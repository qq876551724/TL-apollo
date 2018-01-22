package com.apollo.commons.mq.processors;

import com.alibaba.fastjson.JSONObject;
import com.apollo.commons.mq.utils.enums.ExchangeType;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import com.apollo.commons.mq.utils.pojo.MQCallerInfo;
import com.apollo.commons.mq.utils.pojo.MQLogMessage;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * com.apollo.commons.mq.processors.MQProducerMessagePostProcessor <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/28.
 * @E-mail : 876551724@qq.com
 */
@Component
public class MQProducerMessagePostProcessor implements MessagePostProcessor, BeanFactoryAware {
    private BeanFactory beanFactory;

    public MQProducerMessagePostProcessor() {
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public Message postProcessMessage(Message message) throws AmqpException {
        String json = "";

        json = new String(message.getBody());

        MQLogMessage logMessage = new MQLogMessage((MQBizMessage) JSONObject.parseObject(json, MQBizMessage.class), this.buildProducerCallerInfo());
        ((AmqpTemplate)this.beanFactory.getBean("rabbitTemplate")).convertAndSend(ExchangeType.DEFAULT_TOPIC.getValue(), "st.sys.log", logMessage);
        return message;
    }

    private MQCallerInfo buildProducerCallerInfo() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        int index = -1;

        for(int i = 0; i < elements.length; ++i) {
            if(elements[i].getClassName().startsWith("com.simpletour.mq.producer.MQProducer")) {
                index = i;
            }
        }

        if(index == -1) {
            return null;
        } else {
            String className = elements[index + 1].getClassName();
            String method = elements[index + 1].getMethodName();
            int line = elements[index + 1].getLineNumber();
            return new MQCallerInfo(className, method, Integer.valueOf(line));
        }
    }
}
