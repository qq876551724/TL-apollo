package com.apollo.commons.mq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * com.apollo.commons.mq.consumer.DLQListener <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
public class DLQListener implements ChannelAwareMessageListener {
    private static final String HEADER_X_DEATH = "x-death";
    private static final String HEADER_REASON = "reason";
    private static final String HEADER_REASON_REJECTED = "rejected";
    private static final String HEADER_REASON_EXPIRED = "expired";
    private static final String HEADER_ORI_EXCHANGE = "exchange";
    private static final String HEADER_ORI_ROUTING_KEY = "routing-keys";
    private static final String HEADER_COUNT = "count";
    private final RabbitTemplate rabbitTemplate;
    private final String timeoutExchange;
    private final String timeoutRouteKey;
    private final int maxRetry;
    private final int initialInterval;

    public DLQListener(RabbitTemplate rabbitTemplate, String timeoutExchange, String timeoutRouteKey, int initialInterval, int maxRetry) {
        this.rabbitTemplate = rabbitTemplate;
        this.timeoutExchange = timeoutExchange;
        this.timeoutRouteKey = timeoutRouteKey;
        this.maxRetry = maxRetry;
        this.initialInterval = initialInterval;
    }

    public void onMessage(Message message, Channel channel) throws Exception {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        List<Map<String, Object>> death = (List)headers.get("x-death");
        if(death != null && !death.isEmpty()) {
            DLQListener.StatisticsVo vo = this.statisticsTimes(death);
            if(vo.getRejectTimes() > (long)this.maxRetry) {
                System.out.println("Delete Message: " + message);
                return;
            }

            if(vo.getTimeoutTimes() >= vo.getRejectTimes()) {
                Iterator var6 = death.iterator();

                while(var6.hasNext()) {
                    Map<String, Object> map = (Map)var6.next();
                    if(map.get("reason").equals("rejected")) {
                        String exchange = String.valueOf(map.get("exchange"));
                        String routeKey = String.valueOf(((List)map.get("routing-keys")).get(0));
                        this.rabbitTemplate.send(exchange, routeKey, message);
                        return;
                    }
                }
            } else {
                message.getMessageProperties().setExpiration(this.getNextWaitTime(vo.getRejectTimes()));
                this.rabbitTemplate.send(this.timeoutExchange, this.timeoutRouteKey, message);
            }
        }

    }

    private String getNextWaitTime(long rejectTimes) {
        return String.format("%.0f", new Object[]{Double.valueOf((double)this.initialInterval * Math.pow(2.0D, (double)rejectTimes))});
    }

    private DLQListener.StatisticsVo statisticsTimes(List<Map<String, Object>> death) {
        Assert.isTrue(death != null && !death.isEmpty());
        long rejectCount = 0L;
        long timeoutCount = 0L;
        Iterator var6 = death.iterator();

        while(var6.hasNext()) {
            Map<String, Object> map = (Map)var6.next();
            String reason = (String)map.get("reason");
            long count = Long.parseLong(String.valueOf(map.get("count")));
            if(reason.equals("rejected")) {
                rejectCount += count;
            } else if(reason.equals("expired")) {
                timeoutCount += count;
            }
        }

        return new DLQListener.StatisticsVo(rejectCount, timeoutCount);
    }

    private class StatisticsVo {
        private long rejectTimes;
        private long timeoutTimes;

        public StatisticsVo(long rejectTimes, long timeoutTimes) {
            this.rejectTimes = rejectTimes;
            this.timeoutTimes = timeoutTimes;
        }

        public long getRejectTimes() {
            return this.rejectTimes;
        }

        public long getTimeoutTimes() {
            return this.timeoutTimes;
        }
    }
}
