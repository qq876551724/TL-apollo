package com.apollo.commons.mq.utils.handlers;

import com.alibaba.fastjson.JSONObject;
import com.apollo.commons.mq.utils.enums.ExchangeType;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import com.apollo.commons.mq.utils.pojo.MQCallerInfo;
import com.apollo.commons.mq.utils.pojo.MQEndpoint;
import com.apollo.commons.mq.utils.pojo.MQLogMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import java.io.UnsupportedEncodingException;

/**
 * com.apollo.commons.mq.utils.handlers.LoggingErrorHandler <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
@Component
public class LoggingErrorHandler extends ConditionalRejectingErrorHandler implements ErrorHandler, BeanFactoryAware {
    private BeanFactory beanFactory;

    public LoggingErrorHandler() {
    }

    public LoggingErrorHandler(FatalExceptionStrategy exceptionStrategy) {
        super(exceptionStrategy);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void handleError(Throwable throwable) {
        if(throwable instanceof ListenerExecutionFailedException && !this.causeChainContainsMCE(throwable)) {
            ListenerExecutionFailedException exception = (ListenerExecutionFailedException)throwable;
            String json = "";

            try {
                json = new String(exception.getFailedMessage().getBody(), "UTF-8");
            } catch (UnsupportedEncodingException var7) {
                var7.printStackTrace();
            }

            String className = this.getClass().getCanonicalName();
            MQBizMessage bizMessage = (MQBizMessage) JSONObject.parseObject(json, MQBizMessage.class);
            bizMessage.setEndpointType(MQEndpoint.Type.SYS);
            MQLogMessage logMessage = new MQLogMessage(MQLogMessage.Level.ERROR, bizMessage, new MQCallerInfo(className, "handleError", Integer.valueOf(0)));
            ((AmqpTemplate)this.beanFactory.getBean("rabbitTemplate")).convertAndSend(ExchangeType.DEFAULT_TOPIC.getValue(), "st.sys.log", logMessage);
        }

        super.handleError(throwable);
    }

    private boolean causeChainContainsMCE(Throwable t) {
        for(Throwable cause = t.getCause(); cause != null; cause = cause.getCause()) {
            if(cause instanceof MessageConversionException) {
                return true;
            }
        }

        return false;
    }
}
