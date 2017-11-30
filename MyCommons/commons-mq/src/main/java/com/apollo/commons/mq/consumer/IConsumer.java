package com.apollo.commons.mq.consumer;

import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * com.apollo.commons.mq.consumer.IConsumer <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
public interface IConsumer {
    void handle(@Payload MQBizMessage var1);
}
