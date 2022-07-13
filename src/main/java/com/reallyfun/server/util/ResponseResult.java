package com.reallyfun.server.util;

import java.io.Serializable;

/**
 * 响应结果类
 *
 * @param <E> 响应数据的类型
 */
public class ResponseResult<E> implements Serializable {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 0;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态描述信息
     */
    private String message;
    /**
     * 数据
     */
    private E data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public static <E> ResponseResult<E> getResponseResult() {
        return new ResponseResult<>(OK, null, null);
    }

    public static <E> ResponseResult<E> getResponseResult(Integer code) {
        return new ResponseResult<>(code, null, null);
    }

    public static <E> ResponseResult<E> getResponseResult(String message) {
        return new ResponseResult<>(OK, message, null);
    }

    public static <E> ResponseResult<E> getResponseResult(E data) {
        return new ResponseResult<>(OK, null, data);
    }

    public static <E> ResponseResult<E> getResponseResult(Integer code, String
            message) {
        return new ResponseResult<>(code, message, null);
    }

    public static <E> ResponseResult<E> getResponseResult(String message, E data) {
        return new ResponseResult<>(OK, message, data);
    }
}