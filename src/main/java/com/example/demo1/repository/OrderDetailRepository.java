package com.example.demo1.repository;

import com.example.demo1.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    /**
     * 根据订单号查询详情
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrOrderId(String orderId);
}
