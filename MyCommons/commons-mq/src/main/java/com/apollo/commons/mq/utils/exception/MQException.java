package com.apollo.commons.mq.utils.exception;

import org.springframework.amqp.AmqpException;

/**
 * com.apollo.commons.mq.utils.exception.MQException <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
public class MQException extends AmqpException {
    private static final long serialVersionUID = 123456789L;

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQException(Throwable cause) {
        super(cause);
    }
}
