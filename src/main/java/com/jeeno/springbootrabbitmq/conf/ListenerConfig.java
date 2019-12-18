package com.jeeno.springbootrabbitmq.conf;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 监听器的配置
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 14:23
 */
@Component
public class ListenerConfig {

    /**
     * 设置消息确认的三种模式：
     * AcknowledgeMode.MANUAL 手动确认
     * AcknowledgeMode.NONE   自动确认
     * AcknowledgeMode.AUTO   手动确认
     */
    @Bean("ManualListenerFactory")
    public RabbitListenerContainerFactory myFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory containerFactory=
                new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        // 设置消费者的消息确认模式
        containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return containerFactory;
    }

    /**
     * 支持json消息转化的配置
     */
    @Bean("JsonListenerFactory")
    public RabbitListenerContainerFactory jsonFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory=
                new SimpleRabbitListenerContainerFactory();
        containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        containerFactory.setConnectionFactory(connectionFactory);
        return containerFactory;
    }

}
