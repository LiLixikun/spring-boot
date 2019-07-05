package com.example.demo1.repository;

import com.example.demo1.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository masterRepository;

    @Test
    public void findByBuyerOpenId() {
        //2。0 分页写法
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderMaster> masters = masterRepository.findBybuyerOpenid("abc123", request);
        System.out.println(masters);
    }

    @Test
    public void saveData() {
        OrderMaster master = new OrderMaster();
        master.setOrderId("2019611");
        master.setBuyerAddress("上海金融广场");
        master.setBuyerName("席坤1");
        master.setBuyerPhone("18086460240");
        master.setOrderAmount(new BigDecimal(6.122));
        master.setBuyerOpenid("abc123");

        OrderMaster result = masterRepository.save(master);
        System.out.println(result);
    }
}

