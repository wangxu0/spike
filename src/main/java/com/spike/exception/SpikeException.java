package com.spike.exception;

/**
 * 秒杀异常，所有自定义异常的父类。
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class SpikeException extends RuntimeException {

    public SpikeException() {
    }

    public SpikeException(String message) {
        super(message);
    }

    public SpikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
