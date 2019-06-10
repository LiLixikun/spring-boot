package com.example.demo1.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;


    private String orderId;

    /**商品ID*/
    private String productId;

    /**商品名称*/
    private String productName;

    /**商品价格*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**商品图片*/
    private String productIcon;

}
