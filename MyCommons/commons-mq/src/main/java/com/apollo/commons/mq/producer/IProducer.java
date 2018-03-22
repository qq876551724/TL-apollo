package com.apollo.commons.mq.producer;

import com.apollo.commons.mq.utils.enums.ExchangeType;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * com.apollo.commons.mq.producer.IProducer is written for <br>
 *
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
public interface IProducer {
    void send(ExchangeType var1, String var3, MQBizMessage var2,  MessagePostProcessor var4);

    void sendDefault(String var2, MQBizMessage var1);
}

