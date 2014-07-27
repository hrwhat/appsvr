package com.ray.ppsvr;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * User: Ray
 * Date: 14-5-9
 * Time: 上午10:15
 */
public class HttpUtil {

    public static final ObjectMapper om = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    static HttpClient client = null;
    static {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setMaxConnTotal(10);
        client = httpClientBuilder.build();
    }

    public static String post(String url, String data) throws IOException {

        HttpPost post = new HttpPost(url);
        HttpEntity entity = new StringEntity(data,"UTF-8");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        HttpEntity httpEntity = response.getEntity();
        String res = "";
        if (httpEntity != null) {
            res = EntityUtils.toString(httpEntity, "UTF-8");
            //打印响应内容
            logger.debug("response:" + res);
        }
        return res;
    }


    public static String get(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        HttpEntity httpEntity = response.getEntity();
        String res = "";
        if (httpEntity != null) {
            res = EntityUtils.toString(httpEntity, "UTF-8");
            //打印响应内容
            logger.debug("response:" + res);
        }
        return res;
    }

    public static String post(String url, Map<String, Object> data) throws IOException {
        HttpPost post = new HttpPost(url);
        MultipartEntity entity = new MultipartEntity();
        if(data != null){
            Set<Map.Entry<String, Object>> set = data.entrySet();
            for(Map.Entry<String,Object> entry:set){
                if(entry.getValue() instanceof String){
                    entity.addPart(entry.getKey(), new StringBody(entry.getValue().toString()));
                }
            }
        }
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        HttpEntity httpEntity = response.getEntity();
        String res = "";
        if (httpEntity != null) {
            res = EntityUtils.toString(httpEntity, "UTF-8");
            //打印响应内容
            logger.debug("response:" + res);
        }
        return res;
    }

}
