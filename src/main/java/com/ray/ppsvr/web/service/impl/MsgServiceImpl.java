package com.ray.ppsvr.web.service.impl;

import com.ray.ppsvr.web.dao.MsgDAO;
import com.ray.ppsvr.web.service.BaseService;
import com.ray.ppsvr.web.service.MsgService;
import com.ray.ppsvr.web.vo.DataTableRequest;
import com.ray.ppsvr.web.vo.DataTableVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/3
 * Time: 19:09
 */
@Service
public class MsgServiceImpl extends BaseService implements MsgService {
    @Resource
    private MsgDAO msgDAO;

    @Override
    public DataTableVO query(DataTableRequest dtr) {
        DataTableVO vo = new DataTableVO();
        Map<String, Object> params = prepareParams(dtr);
        List list = queryMsgs(params);
        vo.setDraw(dtr.getDraw());
        long total = countMsgs(params);
        vo.setRecordsTotal(total);
        vo.setRecordsFiltered(total);
        vo.setData(list);
        return vo;
    }

    @Override
    public long countMsgs(Map<String, Object> params) {
        return msgDAO.countMsgs(params);
    }

    @Override
    public List queryMsgs(Map<String, Object> params) {
        return msgDAO.queryMsgs(params);
    }


}
