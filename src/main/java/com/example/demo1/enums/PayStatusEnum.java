package com.example.demo1.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0, "待支付"),
    SUCCESS(1, "支付完成");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
