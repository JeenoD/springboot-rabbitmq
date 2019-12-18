package com.jeeno.springbootrabbitmq.consumer;

import com.jeeno.springbootrabbitmq.conf.RabbitMqConf;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 手动确认消费成功/失败的消费者
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 14:37
 */
@Slf4j
@Component
public class ManualConfirmConsumer {

    @RabbitListener(containerFactory = "ManualListenerFactory",
            bindings = @QueueBinding(
                    value = @Queue(RabbitMqConf.MANUAL_ACK),
                    exchange = @Exchange(value = RabbitMqConf.MANUAL_ACK_EXCHANGE, type = ExchangeTypes.DIRECT),
                    key = "")
    )
    @RabbitHandler
    public void consumer(@Payload String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        log.info("#ManualConfirmConsumer# msg({})", msg);
        try{
//            throw new Exception("error");
            log.info("#ManualConfirmConsumer# consume successfully.");
            // 消息确认,(deliveryTag,multiple是否确认所有消息)
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), true);
        } catch (Exception e) {
            log.error("#ManualConfirmConsumer# error occurred. {}", e.getMessage());
            // 消息拒绝, 三个参数一次为：deliveryTag, multiple, 拒绝后是否将消息放回队列
            channel.basicNack((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false, false);
        }
    }
}
