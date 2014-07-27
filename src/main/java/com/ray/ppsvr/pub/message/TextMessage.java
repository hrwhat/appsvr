package com.ray.ppsvr.pub.message;

/**
 * User: Ray
 * Date: 14-5-19
 * Time: ÏÂÎç5:17
 */
public class TextMessage extends BaseMessage {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "{\n" +
                "  \"touser\": \"" + getTouser() + "\", \n" +
                "  \"msgtype\": \"text\", \n" +
                "  \"text\": {\n" +
                "    \"content\": \"" + getContent() + "\"\n" +
                "   }\n" +
                "}";
    }
}
