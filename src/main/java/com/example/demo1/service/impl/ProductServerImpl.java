package com.example.demo1.service.impl;

import com.example.demo1.dataobject.ProductInfo;
import com.example.demo1.enums.ProductStatusEnums;
import com.example.demo1.repository.ProductInfoRepository;
import com.example.demo1.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo findById(String productId) {
        return repository.findById(productId).get();
    }
}
