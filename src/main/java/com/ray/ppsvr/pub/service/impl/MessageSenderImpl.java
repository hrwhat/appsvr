package com.ray.ppsvr.pub.service.impl;



import com.ray.ppsvr.HttpUtil;
import com.ray.ppsvr.pub.JsonUtil;
import com.ray.ppsvr.pub.SysParameter;
import com.ray.ppsvr.pub.dao.PubDAO;
import com.ray.ppsvr.pub.message.BaseMessage;
import com.ray.ppsvr.pub.message.TextMessage;
import com.ray.ppsvr.pub.service.AccessTokenService;
import com.ray.ppsvr.pub.service.MessageSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 14-5-19
 * Time: 下午5:09
 */
@Service
public class MessageSenderImpl implements MessageSender {

    @Resource
    private AccessTokenService accessTokenService ;

    @Resource
    private PubDAO pubDAO;



    public boolean processMessage(List<Map<String,Object>> list) throws IOException {
        if(list != null && list.size() > 0){
            TextMessage tm;
            for(Map<String, Object> msg:list){
                tm = new TextMessage();
                String openId = getOpenId(msg.get("uid").toString());
                if(openId == null||"".equals(openId.trim())){
                    //还是要写消息表
                }else{
                    tm.setTouser(openId);
                    tm.setContent(getContent(msg.get("act").toString()));
                    send(tm);
                }

            }
        }
        return true;
    }


    public boolean send(BaseMessage message) throws IOException {
        boolean success = false;
        String accessToken = accessTokenService.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        String res = HttpUtil.post(url.replace("ACCESS_TOKEN", accessToken), message.toString());
        if (res != null) {
            Map<String, Object> map = JsonUtil.parseMap(res);
            String errcode = String.valueOf(map.get("errcode"));
            if ("40014".equals(errcode)) {  //不合法的access_token ,再取一次
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
            } else {
                message.setErrcode(errcode);
                message.setErrmsg(String.valueOf(map.get("errmsg")));
                throw new RuntimeException("调用点对点接口失败：" + map.get("errcode") + message.getErrmsg());
            }

        }
        return success;
    }


    private String getOpenId(String studentNo){
        Map<String, Map<String,String >> studentsMap = pubDAO.queryStudentOpenId();
        Map<String,String> student = studentsMap.get(studentNo);
        return student==null?null:student.get("OPEN_ID");
    }

    private  String getContent(String act){
        return SysParameter.getActInfo(act);
    }


    private void logMsg(){

    }
}
