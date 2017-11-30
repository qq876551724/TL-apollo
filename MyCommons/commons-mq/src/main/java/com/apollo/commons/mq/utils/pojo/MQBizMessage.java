package com.apollo.commons.mq.utils.pojo;

import com.apollo.commons.mq.utils.pojo.MQEndpoint.Type;

import java.io.Serializable;

/**
 * com.apollo.commons.mq.utils.pojo.MQBizMessage <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
public class MQBizMessage<T> extends MQMessage {
    private String appId;
    private Type endpointType;
    private String actionType;
    protected T value;

    public MQBizMessage() {
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Type getEndpointType() {
        return this.endpointType;
    }

    public void setEndpointType(Type endpointType) {
        this.endpointType = endpointType;
    }

    public String getActionType() {
        return this.actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
