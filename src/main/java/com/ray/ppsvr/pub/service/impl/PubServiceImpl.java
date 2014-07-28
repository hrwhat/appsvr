package com.ray.ppsvr.pub.service.impl;

import com.ray.ppsvr.pub.dao.PubDAO;
import com.ray.ppsvr.pub.service.PubService;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Ray
 * Date: 14-5-16
 * Time: 下午2:57
 */
@Service
public class PubServiceImpl implements PubService {
    Logger logger = LoggerFactory.getLogger(PubServiceImpl.class);
    private static final String EVENT_SUBSCRIBE = "subscribe";
    private static final String EVENT_UNSUBSCRIBE = "unsubscribe";
    private static final String EVENT_CLICK = "CLICK";
    @Value("${com.ray.ppsvr.validate}")
    private String validate;

    @Resource
    PubDAO pubDAO;

    @Override
    public int saveSubcriber(Element root) {
        String openId = root.element("FromUserName").getText();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("OPEN_ID", openId);
        int result = 0;
        String msgType = root.element("MsgType").getText();
        if ("event".equals(msgType)) {
            String event = root.element("Event").getText();
            logger.debug("event=" + event);


            if (EVENT_SUBSCRIBE.equals(event)) {      //关注

                /*try {
                    pubDAO.deleteSubcriber(params);      //不管怎样，先删除掉有可能存在的老数据
                    pubDAO.addSubcriber(params);
                } catch (Exception e) {
                    logger.error("保存数据出错：" + e);
                    result = -1;
                }*/
            }else if(EVENT_UNSUBSCRIBE.equals(event)){  //取消关注
                pubDAO.deleteSubcriber(params);
                result = 2;
            }else if(EVENT_CLICK.equals(event)){
                String eventKey = root.element("EventKey").getText();
                if("BIND".equals(eventKey)){
//                     result = this.clickBind(openId);
                    result = 6;
                }
            }
        }else if ("text".equals(msgType)){
            String content = root.element("Content").getText();
            String prefix = content.substring(0, 4).toUpperCase();
            if("BDXH".equals(prefix)){
                String studentNo = content.substring(4);
                params.put("STUDENT_NO", studentNo);
                Map<String, String> student = pubDAO.queryStudentByNo(studentNo);
                if(student == null){
                    pubDAO.addSubcriber(params);
                    result = 3;
                }else{
                    String oldOpenId = student.get("OPEN_ID");
                    if(oldOpenId == null || oldOpenId.equals("")){
                        result = 3;
                    }else if(!openId.equals(oldOpenId)){
                        result = 4;
                    }else{
                        result = 1;
                    }
                    pubDAO.updateSubcriber(params);
                }

            } else{
                result = -2;      //什么都不干
            }
        }
        return result;
    }

    /**
     * 用户点击“绑定”菜单时，先查询他是否已绑定，如果已绑定，直接提示已绑定，如果没有，则绑定，并通知其绑定成功
     * @param openId
     * @return
     */
    private int clickBind(String openId){
        int result = 0;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("OPEN_ID", openId);
        Map<String,String> map = pubDAO.queryBindByOpenId(openId);

        if(map == null ||map.get("STUDENT_NO") == null){
            result = 3;     //表示还没有保存OPEN_ID,

            pubDAO.addSubcriber(params);
        }else {
            String accNbr = map.get("ACC_NBR");
            if (accNbr == null || "".equals(accNbr)) {    //已保存OPEN_ID，但没有手机号
                result = 4;

                if (accNbr == null || accNbr.equals("")) {
                    result = 41;  //表示取手机号不成功
                }
                params.put("ACC_NBR", accNbr);
                pubDAO.updateSubcriber(params);
            } else {           //ACC_NBR和OPEN_ID都已保存
                result = 5;
            }
        }
        return result;
    }




    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }
}
