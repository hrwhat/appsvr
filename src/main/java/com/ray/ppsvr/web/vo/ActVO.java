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
    private Map data;

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

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
