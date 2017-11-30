package com.apollo.commons.mq;

import com.alibaba.fastjson.JSONObject;
import com.apollo.commons.mq.consumer.MQConsumer;
import com.apollo.commons.mq.utils.exception.MQException;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by tianlei on 17-3-14.
 *
 * 消息队列中，某个队列的消费者，配置示例如下
 *
 * <rabbit:listener-container connection-factory="connectionFactory" message-converter="fastJsonMessageConverter" error-handler="loggingErrorHandler">
 *      <rabbit:listener ref="orderMessageConsumer" method="consume" queue-names="st.biz.dest.order.queue"/>
 * </rabbit:listener-container>

 * <bean id="orderMessageConsumer" class="com.simpletour.commons.mq.MQConsumerExt"/>
 *
 */
@Log4j2
public class MQConsumerExt extends MQConsumer {

    @Resource
    BeanFactory beanFactory;

    @Resource
    EntityManagerFactory emf;


    private void startSession() {
        EntityManager em = emf.createEntityManager();
        TransactionSynchronizationManager.bindResource(emf, new EntityManagerHolder(em));
    }

    private void closeSession() {
        EntityManagerHolder emHolder = (EntityManagerHolder)TransactionSynchronizationManager.unbindResource(emf);
        EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handle(@Payload MQBizMessage mqBizMessage) throws MQException {
        try {
            startSession();
            MQMessageHandler handler = (MQMessageHandler) beanFactory.getBean(mqBizMessage.getActionType());
            Class messageClazz = Class.forName(mqBizMessage.getActionType());
            handler.handle(((JSONObject) mqBizMessage.getValue()).toJavaObject(messageClazz));
        } catch (ClassNotFoundException e) {
//            log.error(String.format("消息处理失败, 找不到类:%s", mqBizMessage.getActionType()), e);
            // 根据MQBizMessage::ActionType找不到对应的类，可以认为不是我们应该处理的消息，所以直接吞掉异常，使消息从队列中被消费掉
        } catch (BeansException e) {
//            log.error(String.format("消息处理失败，找不到消息(%s)对应MQMessagehandler", mqBizMessage.getActionType()), e);
            // 根据MQBizMessage::ActionType找不到对应的Handler，可以认为不是我们应该处理的消息，所以直接吞掉异常，使消息从队列中被消费掉
       } catch (Throwable e) {
//            log.error("消息处理失败", e);
            // 其他异常有可能是各种情况导致，抛出异常，使消息不会再次加入队列，以免阻塞后续的消息
//            throw new AmqpRejectAndDontRequeueException(e);
            throw new MQException(e);
        } finally {
            closeSession();
        }
    }
}
