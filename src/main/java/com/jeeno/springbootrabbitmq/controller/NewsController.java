package com.jeeno.springbootrabbitmq.controller;

import com.jeeno.springbootrabbitmq.pojo.ReturnDTO;
import com.jeeno.springbootrabbitmq.producer.NewsProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 新闻推送相关的控制层接口
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 9:43
 */
@RestController
public class NewsController {

    @Resource
    private NewsProducer newsProducer;

    @GetMapping("/new")
    public ReturnDTO callNewProducer(){
        newsProducer.newSend("Good Morning. Today's News only for u.");
        return ReturnDTO.builder().status(ReturnDTO.StatusEnum.SUCCESS).msg("新闻随机推送成功").build();
    }

    @GetMapping("/subscribe")
    public ReturnDTO callSubscribeProducer() {
        newsProducer.subscribeContentSend("Fanout Mode. Subscribe content.");
        return ReturnDTO.builder().status(ReturnDTO.StatusEnum.SUCCESS).msg("新闻&订阅消息发送成功").build();
    }

    @GetMapping("/news")
    public ReturnDTO callFanoutProducer() {
        newsProducer.fanoutNewsSend("Fanout Mode. Today's News.");
        return ReturnDTO.builder().status(ReturnDTO.StatusEnum.SUCCESS).msg("交换器向多个消息队列推送新闻成功").build();
    }

}
