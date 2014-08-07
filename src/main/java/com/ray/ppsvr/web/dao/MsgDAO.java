package com.ray.ppsvr.web.dao;

import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/7/30
 * Time: 11:06
 */
public interface MsgDAO {

    long countMsgs(Map<String, Object> params);
    List queryMsgs(Map<String, Object> params);


}
