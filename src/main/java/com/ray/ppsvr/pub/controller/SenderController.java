package com.ray.ppsvr.pub.controller;

import com.ray.ppsvr.RequestUtil;
import com.ray.ppsvr.pub.JsonUtil;
import com.ray.ppsvr.pub.message.TextMessage;
import com.ray.ppsvr.pub.service.MessageSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/7/27
 * Time: 17:28
 */
@Controller
public class SenderController {

    @Resource
    private MessageSender messageSender;

    @RequestMapping(value = "if",method = RequestMethod.POST)
    public @ResponseBody Object send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> res = new HashMap<String, String>();
        String data = RequestUtil.getPostData(request);
        if (data == null || "".equals(data.trim())){
            res.put("resCode", "1");
            res.put("msg", "请求的数据为空");
        } else{
            List<Map<String,Object>> list = JsonUtil.parseList(data);

        }

        return res;
    }

}
