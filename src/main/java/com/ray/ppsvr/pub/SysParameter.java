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
    

	// private static List<BusinessEntity> businessList;

     @PostConstruct
	 public void init()  {
		if (sysParamMap == null) {
			loadData();
		}

	}

	public void loadData() {
		sysParamMap = pubDAO.querySysParams();
	}

	

	public static String getNodeId() {
		return "01";
	}

	public static String getModuleId() {
		return "01";
	}

	public static String getChannelType() {
		return "01";
	}



    public static String getActInfo(String act){
        Map rowMap = (Map) sysParamMap.get("ACT_" + act);
        return String.valueOf( rowMap.get("SYS_VALUE"));
    }

	//
	@SuppressWarnings("unchecked")
	public static String getSeparator() {
		
		Map rowMap = (Map) sysParamMap.get("SEPARATOR");
		String value = (String) rowMap.get("SYS_VALUE");
		return "[" + value + "]";
	}

	// 从接口表加载数据的条数
	@SuppressWarnings("unchecked")
	public static int getLoadSmsNum() {
		
		Map rowMap = (Map) sysParamMap.get("LOAD_SMS_NUM");
		String value = (String) rowMap.get("SYS_VALUE");
		return Integer.parseInt(value);
	}

	public static int getMassLoadSmsNum() {
		
		Map rowMap = (Map) sysParamMap.get("MASS_SMS_LOAD_NUM");
		String value = (String) rowMap.get("SYS_VALUE");
		return Integer.parseInt(value);
	}

	// 当队列满时的时间间隔
	@SuppressWarnings("unchecked")
	public static int getLoadSmsInterval() {
		
		Map rowMap = (Map) sysParamMap.get("LOAD_SMS_INTERVAL");
		String value = (String) rowMap.get("SYS_VALUE");
		return Integer.parseInt(value);
	}

	// 发送队列的上限
	@SuppressWarnings("unchecked")
	public static int getSendQueueLimit() {
		
		Map rowMap = (Map) sysParamMap.get("SEND_QUEUE_LIMIT");
		String value = (String) rowMap.get("SYS_VALUE");
		return Integer.parseInt(value);
	}

	// 从非实时表里加载短信数据线程数
	@SuppressWarnings("unchecked")
	public static int getThreadSmsBill() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_SMS_BILL");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 短信发送线程数最大值
	@SuppressWarnings("unchecked")
	public static int getThreadSmsSendMax() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_SMS_SEND_MAX");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	public static int getSmsGateWayMax() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_GATEWAY_MAX");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 短信发送线程数最小值（初始化的值）
	@SuppressWarnings("unchecked")
	public static int getThreadSmsSendMis() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_SMS_SEND_MIS");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 处理同一工单表超时数据线程数
	@SuppressWarnings("unchecked")
	public static int getThreadOverTime() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_OVER_TIME");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 处理发送成功信息线程数
	@SuppressWarnings("unchecked")
	public static int getThreadSuccess() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_SUCCESS");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 处理发送成功信息状态更新的线程数最大值
	@SuppressWarnings("unchecked")
	public static int getThreadResultMax() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_RESULT_MAX");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 处理发送成功信息状态更新的线程数最小值
	@SuppressWarnings("unchecked")
	public static int getThreadResultMis() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_RESULT_MIS");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 从短信中间表加载数据线程数
	@SuppressWarnings("unchecked")
	public static int getThreadSmsMessage() {
		
		Map rowMap = (Map) sysParamMap.get("THREAD_SMS_MESSAGE");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	// 刷新系统参数的时间间隔
	@SuppressWarnings("unchecked")
	public static int getLoadParamInterval() {
		
		Map rowMap = (Map) sysParamMap.get("LOAD_PARAM_INTERVAL");
		return Integer.valueOf((String) rowMap.get("SYS_VALUE"));
	}

	private static String getSmsContextCode() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_CONTEXT_CODE");
		return ((String) rowMap.get("SYS_VALUE"));
	}

	public static int getSmsLongContextFormat() {
		String contxtCode = getSmsContextCode();
		int msgFormat = 8;
		if (contxtCode != null) {
			String[] cts = contxtCode.split(",");
			if (cts.length >= 2) {
				if (cts[0].equalsIgnoreCase("GBK"))
					msgFormat = 15;
				else if (cts[0].equalsIgnoreCase("iso-10646-ucs-2")) {
					msgFormat = 8;
				}
			}
		}

		return msgFormat;
	}

	public static int getSmsContextFormat() {
		String contxtCode = getSmsContextCode();
		int msgFormat = 15;
		if (contxtCode != null) {
			String[] cts = contxtCode.split(",");
			if (cts.length >= 2) {
				if (cts[1].equalsIgnoreCase("GBK"))
					msgFormat = 15;
				else if (cts[1].equalsIgnoreCase("iso-10646-ucs-2")) {
					msgFormat = 8;
				}
			}
		}

		return msgFormat;
	}

	
	// 加入报文回复日志文件夹目录名称
	@SuppressWarnings("unchecked")
	public static String getSmsReport() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_RESPORT_FOLDER");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
			if (value == null || "".equals(value)) {
				value = "smp";
			}
		} else {
			value = "smp";
		}
		return value;
	}

	// 加入转移成功记录和读取报文日志，并更新到数据库的时间段
	@SuppressWarnings("unchecked")
	public static String getSmsSucessAndReport() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_SUCCESS_RESPORT");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
			if (value == null || "".equals(value)) {
				value = "1;4";
			}
		} else {
			value = "1;4";
		}
		return value;
	}

	// 加入转移成功记录和读取报文日志，并更新到数据库的时间段
	@SuppressWarnings("unchecked")
	public static String[] getResportParameter() {
		
		// SMS_RESPORT_TEST_PARAM
		Map rowMap = (Map) sysParamMap.get("SMS_RESPORT_PARAM");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
			if (value == null || "".equals(value)) {
				value = "30;60;30000";
			}
		} else {
			value = "30;60;30000";
		}
		return value.split(";");
	}

	public static int getResportRunMode() {
		
		// SMS_RESPORT_TEST_PARAM
		Map rowMap = (Map) sysParamMap.get("SMS_RESPORT_RUN_MODE");
		String value = "0";
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
			if (value != null && !"".equals(value)) {
				value = value.split(",")[0];
			}
		}
		return Integer.valueOf(value);
	}

	// 从上行中间表加载数据的条数

	public static int getLoadDeliverNum() {
		
		Map rowMap = (Map) sysParamMap.get("LOAD_DELIVER_NUM");
		String value = (String) rowMap.get("SYS_VALUE");
		return Integer.parseInt(value);
	}

	// 功能开关控制
	public static String getFunctionControl() {
		
		Map rowMap = (Map) sysParamMap.get("FUNCTION_CONTROL");
		return (String) rowMap.get("SYS_VALUE");
	}

	// smp组件所有的URL
	public static String getSmpUrls() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_URL");
		return (String) rowMap.get("SYS_VALUE");
	}

	// 取得通过本地网平均加载数据的信息
	public static String getLoadSmsDataLatn() {
		
		Map rowMap = (Map) sysParamMap.get("BBS_SMS_DATA_LATN");
		return (String) rowMap.get("SYS_VALUE");
	}

	// 从接口表加载没有数据时的时间间隔
	@SuppressWarnings("unchecked")
	public static int getBssLoadNoSmsInternal() {
		
		Map rowMap = (Map) sysParamMap.get("BSS_SMS_DATA");
		String value = (String) rowMap.get("SYS_VALUE");
		int iValue = 0;
		try {
			iValue = Integer.parseInt(value.split(",")[3]);
		} catch (Exception e) {
			iValue = 600000;// 10分钟
		}
		return iValue;
	}

	// 获取需要结果推送的系统代码
	public static String getResultPushSysCode() {
		
		Map rowMap = (Map) sysParamMap.get("RESULT_PUSH_SYSCODE");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;

	}

	// 获取SMPOUTURL
	public static String getSmpOutUrl() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_OUT_URL");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	public static String getIllegalBusinessIds() {
		
		Map rowMap = (Map) sysParamMap.get("MASS_BUSINESSID");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public static String getSmsLoadPriority() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_SEND_LOAD_PRIORITY");
		if (rowMap == null)
			return null;
		else
			return (String) rowMap.get("SYS_VALUE");

	}

	public static long getDefaultBusinessId() {
		
		Map rowMap = (Map) sysParamMap.get("DEFAULT_BUSINESS_ID");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	// public static String getCycleBusiness() {
	// 
	// if (businessList != null && businessList.size() > 0) {
	// StringBuffer buffer = new StringBuffer();
	// for (int i = 0; i < businessList.size(); i++) {
	// BusinessEntity bu = businessList.get(i);
	// Date curDate = new Date();
	// int sendCycle = bu.getSendCycle().intValue();
	// switch (sendCycle) {
	// case 1:
	// try {
	// Date beginDate = DateUtil.stringToDate(bu.getCycleValue().split(",")[0],
	// "yyyy-MM-dd");
	// Date endDate = DateUtil.stringToDate(bu.getCycleValue().split(",")[1],
	// "yyyy-MM-dd");
	// curDate = DateUtil.stringToDate(DateUtil.dateToString(curDate,
	// "yyyy-MM-dd"), "yyyy-MM-dd");
	// if (DateUtil.getDiffTime(beginDate, curDate, 4) < 0 ||
	// DateUtil.getDiffTime(curDate, endDate, 4) < 0) {
	// addBusiness(buffer, bu, i);
	// }
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// break;
	// case 2:
	// if (("," + bu.getCycleValue() + ",").indexOf("," + curDate.getDate() +
	// ",") == -1) {
	// addBusiness(buffer, bu, i);
	// }
	// break;
	// case 3:
	// if (("," + bu.getCycleValue() + ",").indexOf("," + curDate.getDay() +
	// ",") == -1) {
	// addBusiness(buffer, bu, i);
	// }
	// break;
	// }
	//
	// }
	// return buffer.toString();
	// }
	//
	// return null;
	// }

	// public static void addBusiness(StringBuffer buffer, BusinessEntity bu,
	// int i) {
	// buffer.append(bu.getId());
	// if (i < businessList.size() - 1) {
	// buffer.append(",");
	// }
	// }

	public static String getZookeeperAddress() {
		
		Map rowMap = (Map) sysParamMap.get("ZOOKEEPRE_ADDRESS");
		if (rowMap == null) {
			return null;
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

	public static String getSmpoDeployPlan() {
		
		Map rowMap = (Map) sysParamMap.get("SMPO_DEPLOY_PLAN");
		if (rowMap == null) {
			return null;
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

	public static String getSmpDeployPlan() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_DEPLOY_PLAN");
		if (rowMap == null) {
			return null;
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

    public static String getSmpyxDeployPlan() {
        
        Map rowMap = (Map) sysParamMap.get("SMP_YX_DEPLOY_PLAN");
        if (rowMap == null) {
            return null;
        }
        return(String) rowMap.get("SYS_VALUE");
    }

	public static String getSmpDeployPlanDetail() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_DEPLOY_PLAN_DETAIL");
		if (rowMap == null) {
			return null;
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

	public static String getSmpStatDeployPlan() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_STAT_DEPLOY_PLAN");
		if (rowMap == null) {
			return null;
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

	public static String getSmpBindSpNumParam() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_BIND_SPNUM_PARAM");
		if (rowMap == null) {
			return null;
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

	public static long getPriorityAccumSize() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_PRIORITY_ACCUM_SIZE");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getOntimeAccumSize() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_ONTIME_ACCUM_SIZE");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getBusinessAccumSize() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_BUSINESS_ACCUM_SIZE");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getLatnAccumSize() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_LATN_ACCUM_SIZE");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getProcTimeOut() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_PROC_TIMEOUT");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getGateWayLost() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_GATEWAY_LOST");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getGateWayTimeOut() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_GATEWAY_TIMEOUT");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getGateWayBusy() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_GATEWAY_BUSY");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static long getClientLost() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_CLIENT_LOST");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}

	public static String getSmsReportParam() {
		
		Map rowMap = (Map) sysParamMap.get("SMP_REPORT_PARAM");
		if (rowMap == null) {
			return "50000,600000";
		}
		String value = (String) rowMap.get("SYS_VALUE");
		return value;
	}

	public static int getSmsReportDelayNum() {
		String reportParam = getSmsReportParam();
		return Integer.valueOf(reportParam.split(",")[0]);
	}

	public static int getSmsReportDelayTime() {
		String reportParam = getSmsReportParam();
		return Integer.valueOf(reportParam.split(",")[1]);
	}

	public static String getSmsReportErrorCode() {
		String reportParam = getSmsReportParam();
		if (reportParam != null && reportParam.split(",").length >= 3) {
			return reportParam.split(",")[2];
		}
		return "000000";
	}

	public static String getAreaCode() {
		
		Map rowMap = (Map) sysParamMap.get("AREA_CODE");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	public static String getGatewayWsdl() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_GATEWAY_WSDL");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}
	
	
	public static String getGatewayWsdlLATN() {
		
		Map rowMap = (Map) sysParamMap.get("SMS_GATEWAY_WSDL_LATN");
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

	public static boolean getOperationMonitorOnOff() {
		String info = SysParameter.getOperationMonitorOnOffConfig();
		if (info != null && info.split(",").length >= 2 && "1".equals(info.split(",")[0])) {
			return true;
		}
		return false;
	}

	public static String getOperationMonitorUrl() {
		String info = SysParameter.getOperationMonitorOnOffConfig();
		if (info != null && info.split(",").length >= 2) {
			return info.split(",")[1];
		}
		return "";
	}



	public static String getOuterNetBusinessIds() {
		
		Map rowMap = (Map) sysParamMap.get("OUTER_NET_BUSINESS_IDS");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	public static String getOuterNetLatnIds() {
		
		Map rowMap = (Map) sysParamMap.get("OUTER_NET_LATN_IDS");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	public static String getUnitConfig() {
		
		Map rowMap = (Map) sysParamMap.get("UNIT_CONFIG_PATH");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	

	

	

	public static String getDeliverCheckMode() {
		
		Map rowMap = (Map) sysParamMap.get("DELIVER_CHECK_MODE");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	/*
	 * 接口日志保存路径
	 */
	public static String getReportUrl() {
		
		Map rowMap = (Map) sysParamMap.get("REPORT_URL");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}

	public static String getIsagSyscodeBusinessidCfg() {
		
		Map rowMap = (Map) sysParamMap.get("BASE_ON_ISAG_SYSCODE_BUSINESSID");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}
	
	public static String getIsagSmpSendCfg() {
		
		Map rowMap = (Map) sysParamMap.get("BASE_ON_ISAG_SMP_SEND_CONFIG");
		String value = null;
		if (rowMap != null) {
			value = (String) rowMap.get("SYS_VALUE");
		}
		return value;
	}
	
	
	
	public static long getDefaultIsagBusinessId() {
		
		Map rowMap = (Map) sysParamMap.get("DEFAULT_ISAG_BUSINESS_ID");
		long value = 0;
		if (rowMap != null) {
			value = Long.valueOf(String.valueOf(rowMap.get("SYS_VALUE")));
		}
		return value;
	}


    public static String getYxAccessToken() {
        
        Map rowMap = (Map) sysParamMap.get("YX_ACCESS_TOKEN");
        String value = null;
        if (rowMap != null) {
            value = (String) rowMap.get("SYS_VALUE");
        }
        return value;
    }
    public static long getYxAccessTokenRefreshTime() {
        
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
    public static long getYxAccessTokenExpireIn() {
        
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

    public static String getWxAccessToken() {

        Map rowMap = (Map) sysParamMap.get("WX_ACCESS_TOKEN");
        String value = null;
        if (rowMap != null) {
            value = (String) rowMap.get("SYS_VALUE");
        }
        return value;
    }
    public static long getWxAccessTokenRefreshTime() {

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
    public static long getWxAccessTokenExpireIn() {

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
