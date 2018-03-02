package com.apollo.commons.mq.utils.pojo;

import lombok.Data;

/**
 * com.apollo.commons.mq.utils.pojo.MQBaseMessage <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
public class MQBaseMessage {
    protected String event;

    public MQBaseMessage() {
        this.event = this.getClass().getName();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
