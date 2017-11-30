package com.apollo.dubbo.consumer.controller.mq;

import com.apollo.commons.mq.producer.MQProducer;
import com.apollo.commons.mq.utils.enums.ExchangeType;
import com.apollo.commons.mq.utils.enums.KeyInfo;
import com.apollo.commons.mq.utils.pojo.MQBizMessage;
import com.apollo.commons.mq.utils.pojo.MQEndpoint;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * com.apollo.dubbo.consumer.controller.mq.MqController <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : tianlei@simpletour.com
 */
@RestController
@RequestMapping("/")
public class MqController {
    @Autowired
    MQProducer producer;
    @Resource
    private AmqpTemplate amqpTemplate;
    @Resource
    protected MessagePostProcessor processor;
    @RequestMapping("set_mp")
    public String set_mp(Model model) throws Exception {
        NotifyNewOrderMsg notifyNewOrderMsg = new NotifyNewOrderMsg();
        notifyNewOrderMsg.setOrderId(10000L);
        MQBizMessage<NotifyNewOrderMsg> mqBizMessage = new MQBizMessage<NotifyNewOrderMsg>();
        mqBizMessage.setActionType(notifyNewOrderMsg.getEvent());
        mqBizMessage.setValue(notifyNewOrderMsg);
        mqBizMessage.setAppId("123");
        mqBizMessage.setEndpointType(MQEndpoint.Type.PRODUCER);
        System.out.println("通知订单事件：消息体:"+mqBizMessage);
        producer.sendDefault(KeyInfo.NEW_ORDER_SYNC.getKey(),mqBizMessage);
//        amqpTemplate.convertAndSend(ExchangeType.DEFAULT_TOPIC.getValue(),"tl.queue.new", mqBizMessage);
        return "success";
    }
}
