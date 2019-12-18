package com.jeeno.springbootrabbitmq.producer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 新闻的生产者
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 9:19
 */
@Slf4j
@Component
public class NewsProducer {

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 生产新闻内容 （轮询发送给一位消费者）
     * @param content 新闻内容
     */
    public void newSend(String content) {
        log.debug("#NewsProducer# NewSend. content({}), date({})", content,
                new SimpleDateFormat("yyyy-mm-DD hh:MM:ss").format(new Date()));
        amqpTemplate.convertAndSend(RabbitMqConf.NEWS, content);
    }

    /**
     * 订阅消息发送
     */
    public void subscribeContentSend(String content) {
        log.debug("#NewsProducer# SubscribeContentSend. content({}), date({})", content,
                new SimpleDateFormat("yyyy-mm-DD hh:MM:ss").format(new Date()));
        amqpTemplate.convertAndSend(RabbitMqConf.SUBSCRIBE, content);
    }

    /**
     * 新闻内容推送 - 群发模式
     * @param content 新闻内容
     */
    public void fanoutNewsSend(String content) {
        log.debug("#NewsProducer# FanoutSend. content({}), date({})", content,
                new SimpleDateFormat("yyyy-mm-DD hh:MM:ss").format(new Date()));
        // 三个参数依次为 fanoutExchange 名，router key, 推送内容
        amqpTemplate.convertAndSend(RabbitMqConf.FANOUT_PREFIX + RabbitMqConf.NEWS, "", content);
    }

}
