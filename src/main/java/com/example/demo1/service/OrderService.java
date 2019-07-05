package com.example.demo1.service;


import com.example.demo1.dataobject.OrderDetail;
import com.example.demo1.dataobject.OrderMaster;
import com.example.demo1.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {


    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO creat(OrderDTO orderDTO);


    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询用户订单列表
     *
     * @param buyer
     * @param pageable
     * @return
     */
    Page<OrderDTO> findOrderList(String buyer, Pageable pageable);

    /**
     * 查询全部订单
     *
     * @param page
     * @return
     */
    Page<OrderMaster> findAllOrderList(Pageable page);

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String orderId);

    /**
     * 完结订单
     *
     * @param orderId
     * @return
     */
    OrderDTO finishOrder(String orderId);

    /**
     * 订单支付
     *
     * @param orderId
     * @return
     */
    OrderDTO payOrder(String orderId);
}
