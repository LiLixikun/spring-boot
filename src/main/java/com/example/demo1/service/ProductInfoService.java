package com.example.demo1.service;

import com.example.demo1.dataobject.ProductInfo;
import com.example.demo1.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    /**
     * 查询在售商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存
     * @param productInfo
     * @return
     */
    void save(ProductInfo productInfo);

    /**
     * 修改商品信息
     * @param productId
     * @param productInfo
     */
    void updata(String productId,ProductInfo productInfo);

    /**
     * 查询商品详情
     * @param productId
     * @return
     */
    ProductInfo findById(String productId);

    /**
     * 删除商品
     * @param productId
     */
    void delete(String productId);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
