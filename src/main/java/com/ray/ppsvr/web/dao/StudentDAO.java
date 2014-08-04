package com.ray.ppsvr.web.dao;

import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/7/30
 * Time: 11:06
 */
public interface StudentDAO {

    int countStudents();
    List queryStudents(Map<String,Object> params);

    int update(Map<String,Object> params);
    void addStudent(Map<String,Object> params);

    void delStudent(String ids);
}
