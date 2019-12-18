package com.jeeno.springbootrabbitmq.consumer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import com.jeeno.springbootrabbitmq.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 15:36
 */
@Slf4j
@Component
public class JsonMessageConsumer {

    @RabbitListener(containerFactory = "JsonListenerFactory",
            bindings = @QueueBinding(
                    value = @Queue(RabbitMqConf.JSON_MESSAGE),
                    exchange = @Exchange(value = RabbitMqConf.JSON_MESSAGE_EXCHANGE, type = ExchangeTypes.DIRECT),
                    key = "")
    )
    @RabbitHandler
    public void consumer(@Payload User user) {
        log.info("#JsonMessageConsumer# user({})", user);
    }
}
