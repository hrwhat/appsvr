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
public interface MsgService {

    DataTableVO query(DataTableRequest dtr);
    long countMsgs(Map<String, Object> params);
    List queryMsgs(Map<String, Object> params);
}
