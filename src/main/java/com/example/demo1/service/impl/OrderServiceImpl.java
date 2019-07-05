package com.example.demo1.service.impl;

import com.example.demo1.converter.OrderMasterToOrderDTO;
import com.example.demo1.dataobject.OrderDetail;
import com.example.demo1.dataobject.OrderMaster;
import com.example.demo1.dataobject.ProductInfo;
import com.example.demo1.dto.CartDTO;
import com.example.demo1.dto.OrderDTO;
import com.example.demo1.enums.OrderStatusEnum;
import com.example.demo1.enums.PayStatusEnum;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.repository.OrderDetailRepository;
import com.example.demo1.repository.OrderMasterRepository;
import com.example.demo1.service.OrderService;
import com.example.demo1.service.ProductInfoService;
import com.example.demo1.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional //一旦抛错 进行事物回滚
    public OrderDTO creat(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(0);

        List<CartDTO> cartDTOList = new ArrayList<>();

        //生成订单号
        String orderId = KeyUtil.getUniqueKey();

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //1.查询商品价格,库存

            //1.1 根据商品ID去商品详情表查询是否存在
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            if (productInfo.getProductId() == null) {
                throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
            }
            //计算商品总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //入库 同时生成商品订单号
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            //把商品详情的信息copy 到订单详情里面
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //生成订单主表
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //减库存
        productInfoService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster master = orderMasterRepository.findById(orderId).get();
        if (master == null) {
            throw new SellException(ResultEnum.PRODUCT_ORDER_EMPTY);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrOrderId(orderId);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrderList(String buyer, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findBybuyerOpenid(buyer, pageable);

        //把orderMaster 转成orderDTO
        List<OrderDTO> orderDTOS = OrderMasterToOrderDTO.converter(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOS, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderMaster> findAllOrderList(Pageable page) {
        Page<OrderMaster> page1 = orderMasterRepository.findAll(page);
        return page1;
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        //1.判断订单状态是否可以取消
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderId, orderMaster);
            throw new SellException(ResultEnum.ORDER_NO_CANCEL);
        }
        //取消订单 修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateMaster = orderMasterRepository.save(orderMaster);
        if (updateMaster == null) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderId, orderMaster);
            throw new SellException(ResultEnum.ORDER_CANCEL_FAIL);
        }
        //退库存
        OrderDTO orderDTO1 = findOne(orderId);
        if (CollectionUtils.isEmpty(orderDTO1.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO1);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO1.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //如果已经支付需要退款
        if (orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO1;
    }

    @Override
    public OrderDTO finishOrder(String orderId) {

        //判断订单状态
        OrderDTO orderDTO = findOne(orderId);
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STSTUS_ERR);
        }

        //修改订单状态
        OrderMaster master = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, master);
        master.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster result = orderMasterRepository.save(master);
        if (result == null) {
            throw new SellException(ResultEnum.ODER_FINISH_FAIL);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO payOrder(String orderId) {
        OrderDTO orderDTO = findOne(orderId);
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付失败】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STSTUS_ERR);
        }
        OrderMaster master = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, master);
        master.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster result = orderMasterRepository.save(master);
        if (result == null) {
            throw new SellException(ResultEnum.ORDER_PAY_FAIL);
        }
        return orderDTO;
    }
}
