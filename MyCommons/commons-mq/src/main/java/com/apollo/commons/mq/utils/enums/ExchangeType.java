package com.apollo.commons.mq.utils.enums;

/**
 * com.apollo.commons.mq.utils.enums.ExchangeType is written for <br>
 *
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
public enum ExchangeType {
    DEFAULT_TOPIC("tl.topic.default");

    private String value;

    private ExchangeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
