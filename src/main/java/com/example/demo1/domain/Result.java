package com.example.demo1.domain;

/**
 * http返回最外层对面
 */
public class Result <T>{

    /**错误码**/
    private Integer code;

    /**错误信息**/
    private String message;

    /**返回错误信息**/
    private T data;


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
