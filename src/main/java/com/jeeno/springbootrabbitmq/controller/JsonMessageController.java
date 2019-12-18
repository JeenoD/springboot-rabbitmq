package com.jeeno.springbootrabbitmq.controller;

import com.jeeno.springbootrabbitmq.pojo.ReturnDTO;
import com.jeeno.springbootrabbitmq.producer.JsonMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 生成json消息的控制层接口
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 15:31
 */
@Slf4j
@RestController
public class JsonMessageController {

    @Resource
    private JsonMessageProducer jsonMessageProducer;

    @GetMapping("/json")
    public ReturnDTO msgSend() {
        jsonMessageProducer.send("JsonMessage. This is the json message");
        return ReturnDTO.builder().status(ReturnDTO.StatusEnum.SUCCESS).msg("消息推送成功").build();
    }
}
