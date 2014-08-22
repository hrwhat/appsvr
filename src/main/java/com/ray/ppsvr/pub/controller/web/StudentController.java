package com.ray.ppsvr.pub.controller.web;

import com.ray.ppsvr.web.service.StudentService;
import com.ray.ppsvr.web.vo.ActVO;
import com.ray.ppsvr.web.vo.Column;
import com.ray.ppsvr.web.vo.DataTableRequest;
import com.ray.ppsvr.web.vo.DataTableVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    private StudentService studentService;

    @RequestMapping("query")
    public
    @ResponseBody
    Object query(@RequestBody DataTableRequest dtr) {
        return studentService.query(dtr);
    }

    @RequestMapping(value = "edit")
    public
    @ResponseBody
    Object edit(ActVO actVO) {

        return studentService.edit(actVO);
    }

    @RequestMapping("save")
    public @ResponseBody Object save(@RequestParam String studentNo,@RequestParam String studentName,@RequestParam String studentId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("STUDENT_NO",studentNo);
        map.put("NAME",studentName);
        map.put("STUDENT_ID",studentId);
        return studentService.save(map);
    }

}
