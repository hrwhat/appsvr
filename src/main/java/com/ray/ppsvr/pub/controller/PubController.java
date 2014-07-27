package com.ray.ppsvr.pub.controller;

import com.ray.ppsvr.pub.service.PubService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: Ray
 * Date: 14-5-16
 * Time: 下午2:21
 */
@Controller
public class PubController {
    static final Logger logger = LoggerFactory.getLogger(PubController.class);

    @Resource
    PubService pubService;

    @RequestMapping("svc")
    public @ResponseBody Object service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("pubService: " + pubService.getValidate());
        if("1".equals(pubService.getValidate())){  //验证开发者模式
            return this.validate(request, response);
        }

        InputStream is = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder strB = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            strB.append(line);
        }
        logger.debug("strB = " + strB);

        int result = 0;
        Document document = null;
        try {
            document = DocumentHelper.parseText(strB.toString());
        } catch (DocumentException e) {
            result = -1;
            logger.error("解析xml出错：", e);
        }
        String fromUser = "";
        String toUser = "";
        if (document != null) {
            Element root = document.getRootElement();
            result = pubService.saveSubcriber(root);
            fromUser = root.element("FromUserName").getText();
            toUser = root.element("ToUserName").getText();
        }
        String message = "";
        switch (result){
            case -1 : message = "对不起，保存您的关注数据时出现了问题，请输入BDXH+学号来绑定学号！"; break; //保存关注信息失败
            case 0 : message = "欢迎关注！请输入BDXH+学号来绑定学号！"; break;        //成功
            case 1 : message = "欢迎关注！请输入BDXH+学号来绑定学号！"; break;
            case 2 : break; //2表示取消关注，没有消息
            case 3 : message = "您还没有绑定学号，已重新绑定！"; break;
            case 31 : message = "您还没有绑定学号，重新绑定时失败！"; break;
            case 4 : message = "您还没有绑定学号，已重新绑定！"; break;
            case 41 : message = "您还没有绑定手机，重新绑定时获取手机号失败！"; break;
            case 5 : message = "绑定学号成功！"; break;
            case 6 : message = "请输入BDXH+学号来绑定学号，绑定后可以收到该学号相关动态"; break;
            default:break;
        }

        if(!message.equals("")) {
            return  "<xml>\n" +
                    "        <ToUserName><![CDATA[" + fromUser + "]]></ToUserName>\n" +
                    "        <FromUserName><![CDATA[" + toUser + "]]></FromUserName>\n" +
                    "        <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                    "        <MsgType><![CDATA[text]]></MsgType>\n" +
                    "        <Content><![CDATA[" + message + "]]></Content>\n" +
                    "        </xml>";

//            write(response, textXml);
        }
        return "";
    }


    private void write(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        pw.write(msg);
        pw.flush();
        pw.close();
    }

    private String validate(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean valid = false;
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String token = "token"; //
        if (signature == null || timestamp == null || nonce == null
                || echostr == null) {
            logger.error("Error parameter count.");
            write(response, "Error parameter count.");
        }
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] strArr = new String[]{token, timestamp, nonce};
        java.util.Arrays.sort(strArr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        MessageDigest mdSha1 = null;
        try {
            mdSha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert mdSha1 != null;
        mdSha1.update(sb.toString().getBytes());
        byte[] codedBytes = mdSha1.digest();
        String codedString = new BigInteger(1, codedBytes).toString(16);
        // 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于易信
        if (codedString.equals(signature)) {
            logger.info("verify success");
//            write(response, echostr);
            return echostr;
//            return;
        } else {
            logger.warn("Check error.");
//            write(response, "Check error.");
            return "Check error.";
        }
//        return valid;
    }
}
