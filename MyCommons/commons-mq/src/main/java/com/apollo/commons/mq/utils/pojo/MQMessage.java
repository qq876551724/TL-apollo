package com.apollo.commons.mq.utils.pojo;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.apollo.commons.mq.utils.pojo.MQMessage <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
public abstract class MQMessage {
    private String createdTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public MQMessage() {
    }

    public String getCreatedTime() {
        return this.createdTime;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
