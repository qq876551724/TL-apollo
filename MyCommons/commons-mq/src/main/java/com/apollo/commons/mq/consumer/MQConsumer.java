package com.apollo.commons.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;

import com.apollo.commons.mq.utils.enums.ExchangeType;
import com.apollo.commons.mq.utils.exception.MQException;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import com.apollo.commons.mq.utils.pojo.MQCallerInfo;
import com.apollo.commons.mq.utils.pojo.MQEndpoint;
import com.apollo.commons.mq.utils.pojo.MQLogMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * com.apollo.commons.mq.consumer.MQConsumer <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : 876551724@qq.com
 */
public abstract class MQConsumer extends MQEndpoint implements IConsumer, ChannelAwareMessageListener {
    public MQConsumer() {
    }

    public void onMessage(Message message, Channel channel) throws Exception {
        String json = this.getDefaultCharsetMessage(message);
        MQBizMessage msg = new MQBizMessage();

        try {
            msg = (MQBizMessage) JSONObject.parseObject(json, MQBizMessage.class);
        } catch (Throwable var7) {
            var7.printStackTrace();
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

        try {
            this.process(msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception var6) {
            var6.printStackTrace();
//            this.sendErrorLogMsg(msg, var6);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

//    private void sendErrorLogMsg(MQBizMessage msg, Exception e) {
//        StackTraceElement ste = e.getStackTrace()[0];
//        msg.setEndpointType(Type.CONSUMER);
//        MQLogMessage logMessage = new MQLogMessage(Level.ERROR, msg, new MQCallerInfo(ste.getClassName(), ste.getMethodName(), Integer.valueOf(ste.getLineNumber())), e.getMessage());
//        ((AmqpTemplate)this.beanFactory.getBean("rabbitTemplate")).convertAndSend(ExchangeType.DEFAULT_TOPIC.getValue(), "st.sys.log", logMessage);
//    }
//
    private void process(MQBizMessage mqBizMessage) throws MQException {
        this.handle(mqBizMessage);
//        mqBizMessage.setEndpointType(Type.CONSUMER);
//        String className = this.getClass().getCanonicalName();
//        this.template.convertAndSend(ExchangeType.DEFAULT_TOPIC.getValue(), "st.sys.log", new MQLogMessage(mqBizMessage, new MQCallerInfo(className, "handle", Integer.valueOf(0))));
    }

    private String getDefaultCharsetMessage(Message message) {
        String json = "";

        try {
            json = new String(message.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return json;
    }
}
