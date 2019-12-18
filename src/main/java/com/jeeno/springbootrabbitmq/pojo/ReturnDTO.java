package com.jeeno.springbootrabbitmq.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 相应返回统一格式
 * @author Jeeno
 * @version 0.0.1
 * @date 2019/12/15 10:11
 */
@Data
@Builder
@ToString
public class ReturnDTO<T> {

    /**
     * 相应状态
     */
    StatusEnum status;
    /**
     * 返回数据
     */
    T data;
    /**
     * 操作内容
     */
    String msg;

    public enum StatusEnum{
        /**
         * 处理成功
         */
        SUCCESS,
        /**
         * 处理失败
         */
        FAILURE;
    }
}
