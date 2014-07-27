package com.ray.ppsvr.pub.service.impl;

import com.ray.ppsvr.HttpUtil;
import com.ray.ppsvr.pub.SysParameter;
import com.ray.ppsvr.pub.dao.PubDAO;
import com.ray.ppsvr.pub.service.AccessTokenService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Ray
 * Date: 14-5-17
 * Time: 上午11:37
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private final Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);
    private ObjectMapper om = new ObjectMapper();
    @Value("${com.ray.ppsvr.appid}")
    private String appid;
    @Value("${com.ray.ppsvr.appsecret}")
    private String appsecret;
    @Value("${com.ray.ppsvr.accesstoken.url}")
    private String url;

    private String ACCESS_TOKEN = null;
    private long EXPIRE_IN = 0;
    private long REFRESH_TIME = 0;

    @Resource
    private PubDAO pubDAO;


    /**
     * 获取访问凭证
     * @param forced  是否强制，默认为非强制
     * @return
     */
    @Override
    @Transactional
    public synchronized String getAccessToken(boolean forced) {
        boolean needRefresh = false;
        if(!forced) {
            if (ACCESS_TOKEN == null || "null".equals(ACCESS_TOKEN)) {     //为空时需取
                //先从系统参数表里取
                String accessToken = SysParameter.getYxAccessToken();
                if(accessToken == null || accessToken.equals("")){
                    needRefresh = true;
                }else{
                    long refreshTime = SysParameter.getYxAccessTokenRefreshTime();
                    long expireIn = SysParameter.getYxAccessTokenExpireIn();
                    if (System.currentTimeMillis() - refreshTime > expireIn) {    //超时了也要取
                        needRefresh = true;
                    }else{
                        this.ACCESS_TOKEN = accessToken;
                    }
                }
            } else {
                if (System.currentTimeMillis() - this.REFRESH_TIME > this.EXPIRE_IN) {    //超时了也要取
                    needRefresh = true;
                }
            }
            if (!needRefresh) {
                return this.ACCESS_TOKEN;
            }
        }
        String url = this.url.replace("APPID", this.appid).replace("APPSECRET", this.appsecret);
        try {
            String res = HttpUtil.get(url);
            Map map = om.readValue(res,Map.class);
            if(map.get("access_token") != null) {
                this.ACCESS_TOKEN = String.valueOf(map.get("access_token"));
                this.EXPIRE_IN = Long.parseLong(String.valueOf(map.get("expires_in"))) * 1000;
                this.REFRESH_TIME = System.currentTimeMillis();

                this.updateSysParams();
            }
        } catch (IOException e) {
            logger.error("获取访问凭证出错：" + e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logger.error("将访问凭证保存至数据库出错：" + e);
            throw new RuntimeException(e);
        }
        return this.ACCESS_TOKEN;
    }

    private void updateSysParams() throws SQLException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("sysName", "YX_ACCESS_TOKEN");
        params.put("sysValue", this.ACCESS_TOKEN);
        pubDAO.updateSysParam(params);
        params = new HashMap<String, String>();
        params.put("sysName", "YX_ACCESS_TOKEN_REFRESH_TIME");
        params.put("sysValue", String.valueOf(this.REFRESH_TIME));
        pubDAO.updateSysParam(params);
        params = new HashMap<String, String>();
        params.put("sysName", "YX_ACCESS_TOKEN_EXPIRE_IN");
        params.put("sysValue", String.valueOf(this.EXPIRE_IN));
        pubDAO.updateSysParam(params);
    }




    public String getAccessToken(){
        return getAccessToken(false);
    }



    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
