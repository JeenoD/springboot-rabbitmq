package com.jeeno.springbootrabbitmq.conf;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 的相关配置类
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 9:17
 */
@Configuration
public class RabbitMqConf {

    /**
     * 新闻消息队列
     */
    public final static String NEWS = "NEWS_QUEUE";
    /**
     * 订阅消息队列
     */
    public final static String SUBSCRIBE = "SUBSCRIBE_QUEUE";
    /**
     * 交换器群发模式的统一前缀
     */
    public final static String FANOUT_PREFIX = "FANOUT_";

    /**
     * 手动确认消费的队列
     */
    public final static String MANUAL_ACK = "MANUAL_ACK_QUEUE";
    /**
     * 手动确认消费的交换器
     */
    public final static String MANUAL_ACK_EXCHANGE = "MANUAL_ACK_EXCHANGE";

    /**
     * json对象格式的消息队列
     */
    public final static String JSON_MESSAGE = "JSON_MESSAGE_QUEUE";
    /**
     * json对象消息的交换器
     */
    public final static String JSON_MESSAGE_EXCHANGE = "JSON_MESSAGE_EXCHANGE";

    @Bean(name = NEWS)
    public Queue newsQueue() {
        return new Queue(NEWS);
    }

    @Bean(name = SUBSCRIBE)
    public Queue subscribeQueue() {
        return new Queue(SUBSCRIBE);
    }

    @Bean
    public FanoutExchange newsForAll() {
        return new FanoutExchange(FANOUT_PREFIX + NEWS);
    }

    @Bean(name = MANUAL_ACK)
    public Queue manualAckQueue() {
        return new Queue(MANUAL_ACK);
    }

    @Bean
    public DirectExchange manualAckExchange() {
        return new DirectExchange(MANUAL_ACK_EXCHANGE);
    }

    @Bean(name = JSON_MESSAGE)
    public Queue jsonMessage() {
        return new Queue(JSON_MESSAGE);
    }

    @Bean(name = JSON_MESSAGE_EXCHANGE)
    public DirectExchange jsonMessageExchange() {
        return new DirectExchange(JSON_MESSAGE_EXCHANGE);
    }

    /**
     * 綁定新闻推送队列到新闻群发的交换器
     * @return Binding
     */
    @Bean
    public Binding bindingNews2Exchange() {
        return BindingBuilder.bind(newsQueue()).to(newsForAll());
    }

    /**
     * 绑定订阅消息推送队列到群发交换器
     * @return Binding
     */
    @Bean
    public Binding bindingSubscribes2Exchange() {
        return BindingBuilder.bind(subscribeQueue()).to(newsForAll());
    }

}
