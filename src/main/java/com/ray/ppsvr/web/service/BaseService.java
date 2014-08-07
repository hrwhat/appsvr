package com.ray.ppsvr.web.service;

import com.ray.ppsvr.web.vo.Column;
import com.ray.ppsvr.web.vo.DataTableRequest;
import com.ray.ppsvr.web.vo.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/7
 * Time: 10:46
 */
public class BaseService {

    public Map<String, Object> prepareParams(DataTableRequest dtr) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", dtr.getStart());
        params.put("size", dtr.getLength());
        //处理查询列
        List<Column> columns = dtr.getColumns();
        for (Column column : columns) {
            if (column.isSearchable()) {
                params.put(column.getData(), dtr.getSearch().getValue());
            }
        }
        //处理排序列
        List<Order> orders = dtr.getOrder();
        StringBuilder sb = new StringBuilder();
        if (orders.size() > 0) {
            sb.append("order by ");
            for (Order order : orders) {
                Column column = columns.get(order.getColumn());
                sb.append(column.getData()).append(" ").append(order.getDir()).append(",");
            }
        }
        if (sb.length() > 0) {
            params.put("ORDER_BY", sb.substring(0, sb.length() - 1));
        }
        return params;
    }
}
