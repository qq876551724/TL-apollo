package com.apollo.commons.mq.producer;

import com.apollo.commons.mq.utils.enums.ExchangeType;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import com.apollo.commons.mq.utils.pojo.MQEndpoint;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * com.apollo.commons.mq.producer.MQProducer <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
@Component
public class MQProducer extends MQEndpoint implements IProducer {
    @Value("${app.id:tl-mq}")
    private String appId;
    @Resource
    protected MessagePostProcessor processor;

    public MQProducer() {
    }

    public void send(ExchangeType exchangeType,  String routeKey,MQBizMessage mqBizMessage, MessagePostProcessor processor) {
        mqBizMessage.setAppId(this.appId);
        mqBizMessage.setEndpointType(Type.PRODUCER);

        this.template.convertAndSend(exchangeType.getValue(), routeKey, mqBizMessage);
    }

    public void sendDefault(String routeKey,MQBizMessage mqBizMessage) {
        this.send(ExchangeType.DEFAULT_TOPIC,routeKey, mqBizMessage,null);
    }
}
