package com.example.demo1.service.impl;

import com.example.demo1.dataobject.ProductInfo;
import com.example.demo1.dto.CartDTO;
import com.example.demo1.enums.ProductStatusEnums;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.repository.ProductInfoRepository;
import com.example.demo1.service.ProductInfoService;
import com.example.demo1.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServerImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ProductInfo productInfo) {
        String productId= KeyUtil.getUniqueKey();
        productId="P"+productId;
        productInfo.setProductId(productId);
        repository.save(productInfo);
    }

    @Override
    public void delete(String productId) {
        findById(productId);
        repository.deleteById(productId);
    }

    @Override
    public ProductInfo findById(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
        }
        return productInfo;
    }

    @Override
    public void updata(String productId, ProductInfo productInfo) {
        ProductInfo productInfo1 = findById(productId);
        BeanUtils.copyProperties(productInfo, productInfo1);
        repository.save(productInfo1);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
            }
            Integer stocks = productInfo.getProductStock() + cartDTO.getProductStock();
            productInfo.setProductStock(stocks);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
            }
            Integer stocks = productInfo.getProductStock() - cartDTO.getProductStock();
            if (stocks < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERR);
            }
            productInfo.setProductStock(stocks);
            repository.save(productInfo);
        }

    }
}
