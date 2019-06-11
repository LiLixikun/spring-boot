package com.example.demo1.service.impl;

import com.example.demo1.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buy_openid="2019610";
    //生成唯一的orderid
    private final String order_id= KeyUtil.getUniqueKey();

    @Test
    public void creat() {

    }

    @Test
    public void findOne() {
    }

    @Test
    public void findOrderList() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void finishOrder() {
    }

    @Test
    public void payOrder() {
    }
}