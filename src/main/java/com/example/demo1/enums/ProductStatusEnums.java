package com.example.demo1.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnums {
    UP(0, "上架"),
    DOWM(1, "下架");

    private Integer code;

    private String message;


    private ProductStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
