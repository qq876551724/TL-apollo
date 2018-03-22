package com.apollo.dubbo.consumer.controller.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * com.apollo.dubbo.consumer.controller.mq.QueueListenter <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
@Component("queueListenter")
public class QueueListenter implements MessageListener
{

    public void onMessage(Message message)
    {
        String str = "";
        try
        {
            str = new String(message.getBody(), "UTF-8");
            System.out.println("=============监听【QueueListenter】消息"+message);
            System.out.print("=====获取消息"+str);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
