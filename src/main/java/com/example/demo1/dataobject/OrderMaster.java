package com.example.demo1.dataobject;

import com.example.demo1.enums.OrderStatusEnum;
import com.example.demo1.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     * 订单号
     */
    @Id
    private String orderId;

    /**
     * 姓名
     */
    private String buyerName;

    /**
     * 手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openId
     */
    private String buyerOpenid;

    /**
     * 订单总额
     */
    private BigDecimal orderAmount;

    /**
     * 订单支付状态
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 订单状态
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

}
