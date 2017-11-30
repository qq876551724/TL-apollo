package com.apollo.commons.mq.utils.pojo;

import lombok.Data;

/**
 * com.apollo.commons.mq.utils.pojo.MQBaseMessage <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
@Data
public class MQBaseMessage {
    protected String event;

    public MQBaseMessage() {
        this.event = this.getClass().getName();
    }
}