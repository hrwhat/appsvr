package com.ray.ppsvr.web.vo;

/**
 * User: Ray
 * Date: 2014/7/30
 * Time: 15:10
 */
public class Search {
    private String value;
    private boolean regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }
}
