package com.jeeno.springbootrabbitmq.producer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import com.jeeno.springbootrabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * json对象消息的生产者
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 15:23
 */
@Slf4j
@Component
public class JsonMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String content) {
        log.info("#JsonMessageProducer# send content({})", content);
        User user = User.builder().name("Jeeno").age(25).phone("12345678901").message(content).build();
        rabbitTemplate.convertAndSend(RabbitMqConf.JSON_MESSAGE, user);
    }
}
