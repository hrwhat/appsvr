package com.ray.ppsvr;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User: Ray
 * Date: 2014/7/27
 * Time: 17:40
 */
public class RequestUtil {

    public static String getPostData(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder strB = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            strB.append(line);
        }
        return strB.toString();
    }
}
