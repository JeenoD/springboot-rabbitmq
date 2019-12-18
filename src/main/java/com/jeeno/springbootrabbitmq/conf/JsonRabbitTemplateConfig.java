package com.jeeno.springbootrabbitmq.conf;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 传递json对象的rabbittemplate配置
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 15:19
 */
@Component
public class JsonRabbitTemplateConfig {

    @Bean(name = "jsonRabbitTemplate")
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        Jackson2JsonMessageConverter messageConverter =
                new Jackson2JsonMessageConverter();
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置转化类
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
