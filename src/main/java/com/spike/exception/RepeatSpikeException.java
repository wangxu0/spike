package com.spike.exception;

/**
 * 重复秒杀异常(运行期异常)
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class RepeatSpikeException extends SpikeException {

    public RepeatSpikeException() {
    }

    public RepeatSpikeException(String message) {
        super(message);
    }

    public RepeatSpikeException(String message, Throwable cause) {
        super(message, cause);
    }

}
