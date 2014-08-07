package com.ray.ppsvr.web.service.impl;

import com.ray.ppsvr.web.dao.StudentDAO;
import com.ray.ppsvr.web.service.BaseService;
import com.ray.ppsvr.web.service.StudentService;
import com.ray.ppsvr.web.vo.ActVO;
import com.ray.ppsvr.web.vo.DataTableRequest;
import com.ray.ppsvr.web.vo.DataTableVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/3
 * Time: 19:09
 */
@Service
public class StudentServiceImpl extends BaseService implements StudentService {
    @Resource
    private StudentDAO studentDAO;

    @Override
    public DataTableVO query(DataTableRequest dtr) {
        DataTableVO vo = new DataTableVO();
        Map<String, Object> params = prepareParams(dtr);
        List list = queryStudents(params);
        vo.setDraw(dtr.getDraw());
        int total = countStudents(params);
        vo.setRecordsTotal(total);
        vo.setRecordsFiltered(total);
        vo.setData(list);
        return vo;
    }

    @Override
    public int countStudents(Map<String, Object> params) {
        return studentDAO.countStudents(params);
    }

    @Override
    public List queryStudents(Map<String, Object> params) {

        return studentDAO.queryStudents(params);
    }


    public Map edit(ActVO actVO) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        System.out.println("actVO = " + 1);
        if ("edit".equals(actVO.getAction())) {
            studentDAO.update(actVO.getData());
            Map<String, Object> rowMap = new HashMap<String, Object>();
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
