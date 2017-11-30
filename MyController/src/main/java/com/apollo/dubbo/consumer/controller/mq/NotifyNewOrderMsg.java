package com.apollo.dubbo.consumer.controller.mq;

import com.apollo.commons.mq.utils.pojo.MQBaseMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * com.apollo.dubbo.consumer.controller.mq.NotifyNewOrderMsg <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
@Data
public class NotifyNewOrderMsg extends MQBaseMessage {
    Long OrderId;
}
