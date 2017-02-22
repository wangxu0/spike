package com.spike.exception;

/**
 * 秒杀关闭异常(运行期异常)
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class SpikeClosedException extends SpikeException {

    public SpikeClosedException() {
    }

    public SpikeClosedException(String message) {
        super(message);
    }

    public SpikeClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
