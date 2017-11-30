package com.apollo.dubbo.consumer.controller.mq.handler;

import com.apollo.commons.mq.MQMessageHandler;
import com.apollo.dubbo.consumer.controller.mq.NotifyNewOrderMsg;
import com.apollo.entity.order.Order;

/**
 * com.apollo.dubbo.consumer.controller.mq.handler.NotifyNewOrderHandler <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
public class NotifyNewOrderHandler implements MQMessageHandler<NotifyNewOrderMsg> {

    @Override
    public void handle(NotifyNewOrderMsg message) {
        System.out.println("get new order message");

    }
}
