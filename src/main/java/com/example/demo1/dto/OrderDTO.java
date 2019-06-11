package com.example.demo1.dto;

import com.example.demo1.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;

    /**姓名*/
    private String buyerName;

    /**手机号*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家微信openId*/
    private String buyerOpenid;

    /**订单总额*/
    private BigDecimal orderAmount;

    /**订单支付状态*/
    private Integer payStatus;

    /**订单状态*/
    private Integer orderStatus;

    private List<OrderDetail> orderDetailList;
}
