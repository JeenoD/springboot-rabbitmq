package com.jeeno.springbootrabbitmq.controller;

import com.jeeno.springbootrabbitmq.pojo.ReturnDTO;
import com.jeeno.springbootrabbitmq.producer.ManualAckProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 手动确认消费demo的控制层接口
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 14:59
 */
@RestController
@Slf4j
public class ManualAckController {

    @Resource
    private ManualAckProducer manualAckProducer;

    @GetMapping("/manual")
    public ReturnDTO sendContent() {
        manualAckProducer.send("ManualAck, This is the message.");
        return ReturnDTO.builder().msg("待手动确认消费的消息发送成功").status(ReturnDTO.StatusEnum.SUCCESS).build();
    }
}
