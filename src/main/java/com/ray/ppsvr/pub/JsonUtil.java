package com.ray.ppsvr.pub;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: Ray
 * Date: 14-5-17
 * Time: 下午2:37
 */
@SuppressWarnings("unchecked")
public class JsonUtil {

    static ObjectMapper om = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseMap(String str){
        Map<String, Object> map = null;
        try {
            map = om.readValue(str,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List<Map<String, Object>> parseList(String str){
        List<Map<String, Object>> list = null;
        try {
            list = om.readValue(str,new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static <T> T parseObject(String str, Class<T> valueType){
        Object object = null;
        try {
            object = om.readValue(str,valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)object;
    }


}
