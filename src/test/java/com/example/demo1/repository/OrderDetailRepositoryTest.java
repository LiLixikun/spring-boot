package com.example.demo1.repository;

import com.example.demo1.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository detailRepository;

    @Test
    public void findByOrOrderId() {
        List<OrderDetail> detailList = detailRepository.findByOrOrderId("2019610");
        Assert.assertNotEquals(0, detailList.size());
    }

    @Test
    public void saveData() {
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("123456");
        detail.setOrderId("2019610");
        detail.setProductId("654321");
        detail.setProductIcon("hppt://xxx.png");
        detail.setProductName("冰淇淋");
        detail.setProductPrice(new BigDecimal(6.12));
        detail.setProductQuantity(200);

        OrderDetail result = detailRepository.save(detail);
        Assert.assertNotNull(result);
    }
}