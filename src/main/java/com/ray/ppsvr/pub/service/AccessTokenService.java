package com.ray.ppsvr.pub.service;

/**
 * User: Ray
 * Date: 14-5-17
 * Time: 上午11:37
 */
public interface AccessTokenService {

    String getAccessToken(boolean forced);

    String getAccessToken();

    public void reloadAccessTokenFromDB();
}
