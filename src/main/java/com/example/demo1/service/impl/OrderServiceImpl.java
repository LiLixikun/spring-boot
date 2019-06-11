package com.example.demo1.service.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
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

        BigDecimal orderAmount=new BigDecimal(0);

        List<CartDTO> cartDTOList=new ArrayList<>();

        //生成订单号
        String orderId= KeyUtil.getUniqueKey();

        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            //1.查询商品价格,库存

            //1.1 根据商品ID去商品详情表查询是否存在
            ProductInfo productInfo= productInfoService.findById(orderDetail.getProductId());
            if(productInfo.getProductId()==null){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
            }
            //计算商品总价
            orderAmount=orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //入库 同时生成商品订单号
            orderDetail.setDetailId(orderId);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            //把商品详情的信息copy 到订单详情里面
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);

            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //生成订单主表
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //减库存
        productInfoService.decreaseStock(cartDTOList);
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findOrderList(String buyer, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        return null;
    }
}
