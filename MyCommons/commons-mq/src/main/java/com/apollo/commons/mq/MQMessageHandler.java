package com.apollo.commons.mq;


public interface MQMessageHandler<T> {

    void handle(T message);
}
