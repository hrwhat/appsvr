package com.ray.ppsvr.pub.controller.web;

import com.ray.ppsvr.web.dao.StudentDAO;
import com.ray.ppsvr.web.service.StudentService;
import com.ray.ppsvr.web.vo.ActVO;
import com.ray.ppsvr.web.vo.Column;
import com.ray.ppsvr.web.vo.DataTableRequest;
import com.ray.ppsvr.web.vo.DataTableVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/7/30
 * Time: 9:44
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @Resource
    private StudentDAO studentDAO;

    @Resource
    private StudentService studentService;

    @RequestMapping("query")
    public @ResponseBody Object query(HttpServletRequest request){
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("start", start);
        params.put("size", length);
        DataTableVO vo = new DataTableVO();
        List list = studentDAO.queryStudents(params);
        vo.setDraw(draw);
        int total = studentDAO.countStudents();
        vo.setRecordsTotal(total);
        vo.setRecordsFiltered(total);
        vo.setData(list);
        return vo;
    }

    @RequestMapping(value = "edit")
    public @ResponseBody Object edit(ActVO actVO){

        return studentService.edit(actVO);
    }
}
