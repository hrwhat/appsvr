package com.ray.ppsvr.web.vo;

import java.io.Serializable;

/**
 * User: Ray
 * Date: 2014/7/30
 * Time: 15:09
 */
public class Column implements Serializable {

    private String data;
    private String  name;
    private String  searchable;
    private String  orderable;
    private Search search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchable() {
        return searchable;
    }

    public void setSearchable(String searchable) {
        this.searchable = searchable;
    }

    public String getOrderable() {
        return orderable;
    }

    public void setOrderable(String orderable) {
        this.orderable = orderable;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
}
