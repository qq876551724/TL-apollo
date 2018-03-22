package com.apollo.dubbo.consumer.controller.mq;

import com.apollo.commons.mq.utils.pojo.MQBaseMessage;


import java.io.Serializable;

/**
 * com.apollo.dubbo.consumer.controller.mq.NotifyNewOrderMsg <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
public class NotifyNewOrderMsg extends MQBaseMessage {
    Long OrderId;

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }
}
