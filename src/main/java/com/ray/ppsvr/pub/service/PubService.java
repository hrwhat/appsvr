package com.ray.ppsvr.pub.service;

import org.dom4j.Element;

/**
 * User: Ray
 * Date: 14-5-16
 * Time: 下午2:57
 */
public interface PubService {

    int saveSubcriber(Element root);

    public String getValidate() ;
}
