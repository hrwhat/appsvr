package com.ray.ppsvr.pub.controller.web;

import com.ray.ppsvr.web.dao.MsgDAO;
import com.ray.ppsvr.web.vo.DataTableVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/6
 * Time: 16:58
 */
@RequestMapping("msg")
@Controller
public class MsgController {

    @Resource
    private MsgDAO msgDAO;

    @RequestMapping("query")
    public @ResponseBody
    Object query(HttpServletRequest request){
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("start", start);
        params.put("size", length);
        DataTableVO vo = new DataTableVO();
        List list = msgDAO.queryMsgs(params);
        vo.setDraw(draw);
        long total = msgDAO.countMsgs();
        vo.setRecordsTotal(total);
        vo.setRecordsFiltered(total);
        vo.setData(list);
        return vo;
    }
}
