package com.example.demo1.VO;

import lombok.Data;

@Data
public class ResultVo<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回实体
     */
    private T data;
}
