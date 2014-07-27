package com.tydic.uccp.pubsvr;

import com.ray.ppsvr.HttpUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * User: Ray
 * Date: 14-5-16
 * Time: 下午2:51
 */
public class MyTest {
    String url = "http://127.0.0.1:8081/svc?signature=&timestamp=&nonce=&echostr=echostr";

    @Test
    public void main() throws IOException{

        String data = " <xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName>OPEN_ID</FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[event]]></MsgType>\n" +
                " <Content><![CDATA[this is a test]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " <Event>subscribe</Event>\n" +
                " </xml>";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            try {
                HttpUtil.post(url, data.replace("OPEN_ID", "open_id"+i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("last time = " +(System.currentTimeMillis() - startTime));
    }

    @Test
    public void clickBind() throws IOException{
        String data = " <xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName>ad7dedd31bcb7844</FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[event]]></MsgType>\n" +
                " <Content><![CDATA[this is a test]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " <Event>CLICK</Event>\n" +
                " <EventKey>BIND</EventKey>\n" +
                " </xml>";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(i);
            try {
                HttpUtil.post(url, data.replace("OPEN_ID", "open_id"+i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("last time = " +(System.currentTimeMillis() - startTime));
    }


    @Test
    public void bind(){
        String data = " <xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName>ad7dedd31bcb7844</FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[BDXH2001]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(i);
            try {
                HttpUtil.post(url, data.replace("OPEN_ID", "open_id"+i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("last time = " +(System.currentTimeMillis() - startTime));
    }


    @Test
    public void send(){
        String data = "[{\"uid\":2001,\"act\":1}]";
        url = "http://127.0.0.1:8081/if";
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(i);
            try {
                HttpUtil.post(url, data.replace("OPEN_ID", "open_id"+i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("last time = " +(System.currentTimeMillis() - startTime));
    }
}
