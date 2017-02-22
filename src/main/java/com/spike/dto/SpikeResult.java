package com.spike.dto;

/**
 * 封装json返回结果
 * @author wangxu
 * blog：http://www.cnblogs.com/wxisme/
 * github：https://github.com/wxisme
 */
public class SpikeResult<T> {

    private boolean success;

    private T data;

    private String error;

    public SpikeResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SpikeResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
