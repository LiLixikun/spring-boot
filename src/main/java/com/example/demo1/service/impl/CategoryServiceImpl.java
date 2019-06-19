package com.example.demo1.service.impl;

import com.example.demo1.dataobject.ProductCategory;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.repository.ProductCategoryRepository;
import com.example.demo1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findById(Integer categoryId) throws SellException{
        ProductCategory productCategory= productCategoryRepository.findById(categoryId).orElse(null);
        if(productCategory==null){
            throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
        }
        return productCategory;
    }

    @Override
    public List<ProductCategory> findAll() {
       return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteById(Integer categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    @Override
    public void deleteAll(List<Integer> integerList) {
        productCategoryRepository.deleteAll();
    }
}
