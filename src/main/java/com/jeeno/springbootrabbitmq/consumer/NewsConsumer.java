package com.jeeno.springbootrabbitmq.consumer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 新闻的消费者
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 9:40
 */
@Slf4j
@Component
public class NewsConsumer {

    @RabbitHandler
    @RabbitListener(queues = RabbitMqConf.NEWS)
    public void newReceived(String msg) {
        // TODO 此处添加新闻消息的消费逻辑代码
        log.info("#NewsConsumer# received message:" + msg);
    }

}
