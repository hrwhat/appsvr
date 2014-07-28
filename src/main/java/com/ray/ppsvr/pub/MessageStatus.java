package com.ray.ppsvr.pub;

/**
 * User: Ray
 * Date: 2014/7/28
 * Time: 16:50
 */
public enum MessageStatus {
    NO_OPEN_ID("没有绑定账号", 1),SENT("已发送", 2),SEND_ERROR("发送异常", 3) ;


    private String name;
    private int index;
    private MessageStatus(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String getNameByIndex(int index){
        for (MessageStatus c : MessageStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
