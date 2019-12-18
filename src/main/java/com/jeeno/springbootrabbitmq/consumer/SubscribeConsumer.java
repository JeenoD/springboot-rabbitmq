package com.jeeno.springbootrabbitmq.consumer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 10:40
 */
@Slf4j
@Component
public class SubscribeConsumer {

    @RabbitHandler
    @RabbitListener(queues = RabbitMqConf.SUBSCRIBE)
    public void subscribeReceived(String msg) {
        // TODO 此处添加订阅消息的消费逻辑代码
        log.info("#SubscribeConsumer# received message:" + msg);
    }

}
