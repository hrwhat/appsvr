package com.ray.ppsvr.pub.dao;

import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * User: Ray
 * Date: 14-5-16
 * Time: 下午4:53
 */
public interface PubDAO {

    void addSubcriber(Map<String, Object> params);

    void deleteSubcriber(Map<String, Object> params);

    void updateSubcriber(Map<String, Object> params);

    Map<String, String> queryBindByOpenId(String openId);

    @MapKey("SYS_NAME")
    Map<String,Map<String,String>> querySysParams();

    void updateSysParam(Map<String, String> params);

    @MapKey("STUDENT_NO")
    Map<String,Map<String,String>> queryStudentOpenId();
}
