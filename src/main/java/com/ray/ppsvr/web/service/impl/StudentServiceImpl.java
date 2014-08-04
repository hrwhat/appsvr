package com.ray.ppsvr.web.service.impl;

import com.ray.ppsvr.web.dao.StudentDAO;
import com.ray.ppsvr.web.service.StudentService;
import com.ray.ppsvr.web.vo.ActVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/3
 * Time: 19:09
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDAO studentDAO;

    public Map edit(ActVO actVO) {
        Map resMap = new HashMap();
        System.out.println("actVO = " + 1);
        if ("edit".equals(actVO.getAction())) {
            studentDAO.update(actVO.getData());
            Map rowMap = new HashMap();
            rowMap.putAll(actVO.getData());
            rowMap.put("DT_RowId", actVO.getId());
            resMap.put("row", rowMap);
        } else if ("create".equals(actVO.getAction())) {
            studentDAO.addStudent(actVO.getData());

        } else if ("remove".equals(actVO.getAction())) {
            studentDAO.delStudent(actVO.getIds());
        }

        return resMap;
    }
}
