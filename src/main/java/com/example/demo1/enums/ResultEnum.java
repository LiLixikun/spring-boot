package com.example.demo1.enums;

public enum ResultEnum {

    SUCCESS(200, "成功"),
    FAIL(-1, "失败"),
    UNLOGIN(-2, "未登陆");

    private Integer code;

    private String message;

    private ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
