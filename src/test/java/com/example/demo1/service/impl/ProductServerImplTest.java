package com.example.demo1.service.impl;

import com.example.demo1.dataobject.ProductInfo;
import com.example.demo1.enums.ProductStatusEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServerImplTest {

    @Autowired
    private ProductServerImpl productServer;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos=productServer.findUpAll();
        Assert.assertEquals(0,productInfos.size());
    }

    @Test
    public void findAll() {
        PageRequest request=new PageRequest(1,10);
        Page<ProductInfo> list=productServer.findAll(request);
        System.out.println(list.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo("234567","蔬菜",new BigDecimal(3.3),120,"苹果真好吃","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2153937626,1074119156&fm=27&gp=0.jpg",1,0);
        ProductInfo result= productServer.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById() {
        ProductInfo productInfo=productServer.findById("123456");
        System.out.println(productInfo);
        Assert.assertNotNull(productInfo);
    }

}