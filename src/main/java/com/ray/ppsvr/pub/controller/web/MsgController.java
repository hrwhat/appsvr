package com.ray.ppsvr.pub.controller.web;

import com.ray.ppsvr.web.service.MsgService;
import com.ray.ppsvr.web.vo.DataTableRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * User: Ray
 * Date: 2014/8/6
 * Time: 16:58
 */
@RequestMapping("msg")
@Controller
public class MsgController {

    @Resource
    private MsgService msgService;

    @RequestMapping("query")
    public
    @ResponseBody
    Object query(@RequestBody DataTableRequest dtr) {

        return msgService.query(dtr);
    }
}
