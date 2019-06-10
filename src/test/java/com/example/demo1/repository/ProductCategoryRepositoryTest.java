package com.example.demo1.repository;

import com.example.demo1.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    //测试数据不会影响数据库数据
    @Transactional
    public void findOne() {
        Optional<ProductCategory> projectCategory = repository.findById(1);
        System.out.println(projectCategory);
    }
}