package com.apollo.commons.mq.utils.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * com.apollo.commons.mq.utils.converter.FastJsonMessageConverter <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
@Component
public class FastJsonMessageConverter extends AbstractJsonMessageConverter {
    public FastJsonMessageConverter() {
        this.setCreateMessageIds(true);
    }

    protected Message createMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        Object var3 = null;

        byte[] bytes;
        try {
            String json = JSON.toJSONString(o);
            bytes = json.getBytes(this.getDefaultCharset());
        } catch (UnsupportedEncodingException var5) {
            throw new MessageConversionException("Failed to convert message content", var5);
        }

        this.initMessageProperties(messageProperties, bytes);
        return new Message(bytes, messageProperties);
    }

    public Object fromMessage(Message message) throws MessageConversionException {
        String json = "";

        try {
            json = new String(message.getBody(), this.getDefaultCharset());
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        try {
            MQBizMessage msg = (MQBizMessage) JSONObject.parseObject(json, MQBizMessage.class);
            return msg;
        } catch (Throwable var4) {
            throw new MessageConversionException("failed to parse string(" + json + ") to MQBizMessage", var4);
        }
    }

    private void initMessageProperties(MessageProperties messageProperties, byte[] bytes) {
        messageProperties.setContentType("application/json");
        messageProperties.setContentEncoding(this.getDefaultCharset());
        if(bytes != null) {
            messageProperties.setContentLength((long)bytes.length);
        }

    }
}
