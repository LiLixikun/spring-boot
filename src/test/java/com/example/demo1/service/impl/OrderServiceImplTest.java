package com.example.demo1.service.impl;

import com.example.demo1.dataobject.OrderDetail;
import com.example.demo1.dataobject.OrderMaster;
import com.example.demo1.dto.OrderDTO;
import com.example.demo1.enums.OrderStatusEnum;
import com.example.demo1.enums.PayStatusEnum;
import com.example.demo1.service.OrderService;
import com.example.demo1.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buy_openid = "2019610";
    //生成唯一的orderid
    private final String order_id = "1560310274973632455";

    @Test
    public void creat() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("上海陆家嘴金融广场");
        orderDTO.setBuyerName("席坤");
        orderDTO.setBuyerOpenid(buy_openid);
        orderDTO.setBuyerPhone("18086460240");

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail detail1 = new OrderDetail();
        detail1.setProductQuantity(2);
        detail1.setProductId("345678");

        orderDetailList.add(detail1);

        OrderDetail detail2 = new OrderDetail();
        detail2.setProductQuantity(1);
        detail2.setProductId("234567");
        orderDetailList.add(detail2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.creat(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(order_id);
        log.info("orderDTO+{}", orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findOrderList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderDTO> orderDTOList = orderService.findOrderList(buy_openid, pageRequest);
        Assert.assertNotNull(orderDTOList);
    }

    @Test
    public void cancelOrder() {
        OrderDTO orderDTO = orderService.cancelOrder(order_id);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderStatus());
    }

    @Test
    public void finishOrder() {
        OrderDTO orderDTO = orderService.finishOrder(order_id);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), orderDTO.getOrderStatus());
    }

    @Test
    public void payOrder() {
        OrderDTO orderDTO = orderService.payOrder(order_id);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDTO.getPayStatus());
    }

    @Test
    public void findAllOrderList() {
        PageRequest request = PageRequest.of(0, 20);
        Page<OrderMaster> detailPage = orderService.findAllOrderList(request);
        log.info("查询出来的全部订单是={}", detailPage);
        Assert.assertNotNull(detailPage);
    }
}