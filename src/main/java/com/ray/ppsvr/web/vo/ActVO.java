package com.ray.ppsvr.web.vo;

import java.util.Map;

/**
 * User: Ray
 * Date: 2014/8/3
 * Time: 16:39
 */
public class ActVO {

    private String action;
    private String id;
    private String ids;
    private Map<String, Object> data;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
