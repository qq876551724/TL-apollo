package com.apollo.commons.mq.utils.pojo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * com.apollo.commons.mq.utils.pojo.MQEndpoint <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
@Component
public class MQEndpoint implements BeanFactoryAware {
    @Resource(name="amqpTemplate")
    protected AmqpTemplate template;
    @Resource
    protected BeanFactory beanFactory;

    public MQEndpoint() {
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public static enum Type {
        PRODUCER("生产者"),
        CONSUMER("消费者"),
        SYS("MQ系统");

        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
