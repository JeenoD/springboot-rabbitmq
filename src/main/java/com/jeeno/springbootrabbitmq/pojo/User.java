package com.jeeno.springbootrabbitmq.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 15:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 消息内容
     */
    private String message;
}
