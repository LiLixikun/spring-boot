package com.example.demo1.service;

import com.example.demo1.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    void save(ProductCategory productCategory);

    void deleteById(Integer categoryId);

    void deleteAll(List<Integer> integerList);
}
