package com.ray.ppsvr.pub.service.impl;


import com.ray.ppsvr.HttpUtil;
import com.ray.ppsvr.pub.JsonUtil;
import com.ray.ppsvr.pub.MessageStatus;
import com.ray.ppsvr.pub.SysParameter;
import com.ray.ppsvr.pub.dao.PubDAO;
import com.ray.ppsvr.pub.message.BaseMessage;
import com.ray.ppsvr.pub.message.TextMessage;
import com.ray.ppsvr.pub.service.AccessTokenService;
import com.ray.ppsvr.pub.service.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 14-5-19
 * Time: 下午5:09
 */
@Service
public class MessageSenderImpl implements MessageSender {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccessTokenService accessTokenService;

    @Value("${com.ray.ppsvr.resend.times}")
    private int RESEND_TIMES;

    @Resource
    private PubDAO pubDAO;


    public boolean processMessage(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            TextMessage tm;
            for (Map<String, Object> msg : list) {
                tm = new TextMessage();
                Map<String, Object> msgLog = new HashMap<String, Object>();
                Map<String, String> student = pubDAO.queryStudentByNo(msg.get("uid").toString());
                if (student == null) {
                    logger.warn("学号[" + msg.get("uid").toString() + "]不存在，被忽略");
                    continue;
                }
                String openId = student.get("OPEN_ID");

                int status = 0;
                String err = "";
                if (openId == null || "".equals(openId.trim())) {
                    //还是要写消息表
                    status = MessageStatus.NO_OPEN_ID.getIndex();
                } else {
                    tm.setTouser(openId);
                    tm.setContent("家长，您好，学生" + student.get("NAME") + "于" + msg.get("time") + getContent(msg.get("act").toString()) + "，请知悉！");
                    try {
                        status = (send(tm) ? MessageStatus.SENT : MessageStatus.SEND_ERROR).getIndex();
                    } catch (Exception e) {
                        logger.error("发送消息失败", e);
                        status = 3;
                        err = "发送消息失败:" + e.getMessage();
                    }
                }
                msgLog.put("TO_USER", msg.get("uid").toString());
                msgLog.put("CONTENT", tm.getContent());
                msgLog.put("ACT", msg.get("act"));
                msgLog.put("MSG_TIME", msg.get("time"));
                msgLog.put("STATUS", status);
                msgLog.put("ERR_MSG", err);
                pubDAO.addMsg(msgLog);
            }
        }
        return true;
    }


    public boolean send(BaseMessage message) {
        if (message.getSendTimes() == RESEND_TIMES) {
            return false;
        }
        message.setSendTimes(message.getSendTimes() + 1);
        boolean success = false;
        String accessToken = accessTokenService.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        try {
            String res = HttpUtil.post(url.replace("ACCESS_TOKEN", accessToken), message.toString());
            if (res != null) {
                Map<String, Object> map = JsonUtil.parseMap(res);
                String errcode = String.valueOf(map.get("errcode"));
                //不合法的access_token ,或者access_token超时再取一次
                if ("40014".equals(errcode) || "42001".equals(errcode)) {
                    accessToken = accessTokenService.getAccessToken(true);
                    res = HttpUtil.post(url.replace("ACCESS_TOKEN", accessToken), message.toString());

                    if (res != null) {
                        map = JsonUtil.parseMap(res);
                        errcode = String.valueOf(map.get("errcode"));
                        if ("0".equals(errcode)) {
                            success = true;
                        } else {
                            message.setErrcode(errcode);
                            message.setErrmsg(String.valueOf(map.get("errmsg")));
                            throw new RuntimeException("调用点对点接口失败：" + map.get("errcode"));
                        }
                    }
                } else if ("0".equals(errcode)) {
                    success = true;
                } else if ("40001".equals(errcode)) {  //有另一个副本已经获取了新的ACCESS_TOKEN,需重新从数据库加载
                    accessTokenService.reloadAccessTokenFromDB();
                    send(message);
                } else {

                    message.setErrcode(errcode);
                    message.setErrmsg(String.valueOf(map.get("errmsg")));
                    logger.error(message.toString());
                    logger.error(accessToken);
                    throw new RuntimeException("重发" + message.getSendTimes() + "次后失败：" + map.get("errcode") + message.getErrmsg());
                }


            }
        } catch (Exception e) {
            success = false;
            logger.error("调用点对点接口失败", e);
            send(message);
        }
        return success;
    }


    private String getOpenId(String studentNo) {
        Map<String, Map<String, String>> studentsMap = pubDAO.queryStudentOpenId();
        Map<String, String> student = studentsMap.get(studentNo);
        return student == null ? null : student.get("OPEN_ID");
    }

    private String getContent(String act) {
        return SysParameter.getActInfo(act);
    }

}
