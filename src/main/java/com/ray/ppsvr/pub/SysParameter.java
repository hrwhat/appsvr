package com.ray.ppsvr.pub;


import com.ray.ppsvr.pub.dao.PubDAO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
public class SysParameter {
    
    @Resource
    private PubDAO pubDAO;

	@SuppressWarnings("unchecked")
	private static Map<String,Map<String,String>> sysParamMap;


     @PostConstruct
	 public void init()  {
		if (sysParamMap == null) {
			loadData();
		}

	}

	public void loadData() {
		sysParamMap = pubDAO.querySysParams();
	}


    public static String getActInfo(String act){
        Map rowMap = (Map) sysParamMap.get("ACT_" + act);
        return String.valueOf( rowMap.get("SYS_VALUE"));
    }


	public static String getAreaCode() {
		
		Map rowMap = (Map) sysParamMap.get("AREA_CODE");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}


	private static String getOperationMonitorOnOffConfig() {
		
		Map rowMap = (Map) sysParamMap.get("OPERATION_MONITOR_ON_OFF");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}


    public String getYxAccessToken() {
        
        Map rowMap = (Map) sysParamMap.get("YX_ACCESS_TOKEN");
        String value = null;
        if (rowMap != null) {
            value = (String) rowMap.get("SYS_VALUE");
        }
        return value;
    }
    public long getYxAccessTokenRefreshTime() {
        
        Map rowMap = (Map) sysParamMap.get("YX_ACCESS_TOKEN_REFRESH_TIME");
        long value = 0;
        if (rowMap != null) {
            Object o= rowMap.get("SYS_VALUE");
            if(o != null && !o.toString().equals("")){
                value = Long.parseLong(o.toString());
            }
        }
        return value;
    }
    public long getYxAccessTokenExpireIn() {
        
        Map rowMap = (Map) sysParamMap.get("YX_ACCESS_TOKEN_EXPIRE_IN");
        long value = 0;
        if (rowMap != null) {
            Object o= rowMap.get("SYS_VALUE");
            if(o != null && !o.toString().equals("")){
                value = Long.parseLong(o.toString());
            }
        }
        return value;
    }

    public String getWxAccessToken() {

        Map rowMap = (Map) sysParamMap.get("WX_ACCESS_TOKEN");
        String value = null;
        if (rowMap != null) {
            value = (String) rowMap.get("SYS_VALUE");
        }
        return value;
    }
    public long getWxAccessTokenRefreshTime() {

        Map rowMap = (Map) sysParamMap.get("WX_ACCESS_TOKEN_REFRESH_TIME");
        long value = 0;
        if (rowMap != null) {
            Object o= rowMap.get("SYS_VALUE");
            if(o != null && !o.toString().equals("")){
                value = Long.parseLong(o.toString());
            }
        }
        return value;
    }
    public long getWxAccessTokenExpireIn() {

        Map rowMap = (Map) sysParamMap.get("WX_ACCESS_TOKEN_EXPIRE_IN");
        long value = 0;
        if (rowMap != null) {
            Object o= rowMap.get("SYS_VALUE");
            if(o != null && !o.toString().equals("")){
                value = Long.parseLong(o.toString());
            }
        }
        return value;
    }
}
