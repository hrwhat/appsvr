package com.ray.ppsvr.pub.service;


import com.ray.ppsvr.pub.message.BaseMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/7/4
 * Time: 11:12
 */
public interface MessageSender {

    boolean processMessage(List<Map<String,Object>> list) throws IOException;
    boolean send(BaseMessage message) throws IOException;
}
