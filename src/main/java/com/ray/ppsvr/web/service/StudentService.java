package com.ray.ppsvr.web.service;

import com.ray.ppsvr.web.vo.ActVO;
import com.ray.ppsvr.web.vo.DataTableRequest;
import com.ray.ppsvr.web.vo.DataTableVO;

import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/3
 * Time: 19:09
 */
public interface StudentService {

    DataTableVO query(DataTableRequest dtr);
    int countStudents(Map<String,Object> params);
    List queryStudents(Map<String,Object> params);
    Map edit(ActVO actVO);

    String save(Map<String,Object> param);
}
