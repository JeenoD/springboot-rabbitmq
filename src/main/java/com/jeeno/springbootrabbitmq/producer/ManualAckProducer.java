package com.jeeno.springbootrabbitmq.producer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 14:55
 */
@Slf4j
@Component
public class ManualAckProducer {

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 生产消息
     */
    public void send(String content) {
        log.debug("#ManualAckProducer# content({})", content);
        amqpTemplate.convertAndSend(RabbitMqConf.MANUAL_ACK_EXCHANGE, "", content);
    }
}
