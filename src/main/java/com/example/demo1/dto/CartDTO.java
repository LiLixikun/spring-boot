package com.example.demo1.dto;

import lombok.Data;

@Data
public class CartDTO {

    /**
     * 购物车实体
     *
     * @param productId
     * @param productStock
     */
    public CartDTO(String productId, Integer productStock) {

        /**商品ID*/
        this.productId = productId;

        /**数量*/
        this.productStock = productStock;
    }

    private String productId;

    private Integer productStock;
}
