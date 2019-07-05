package com.example.demo1.controller;

import com.example.demo1.VO.ResultVo;
import com.example.demo1.converter.OrderFormToOrderDTO;
import com.example.demo1.dataobject.OrderDetail;
import com.example.demo1.dataobject.OrderMaster;
import com.example.demo1.dto.OrderDTO;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.form.OrderForm;
import com.example.demo1.service.OrderService;
import com.example.demo1.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVo<Map<String, String>> creat(@Valid OrderForm orderForm,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.FORM_ERR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormToOrderDTO.converter(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("创建订单购物车不能为空");
            throw new SellException(ResultEnum.CART_NOT_NULL);
        }

        OrderDTO result = orderService.creat(orderDTO);
        log.info("订单详情={}", result);
        if (result == null) {
            throw new SellException(ResultEnum.ORDER_CREAT_FAIL);
        }
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtils.success(map);
    }

    @GetMapping("/orderList")
    public ResultVo<List<OrderDetail>> orderList(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        PageRequest request = PageRequest.of(pageNum - 1, pageSize);
        Page<OrderMaster> detailPage = orderService.findAllOrderList(request);
        return ResultVoUtils.success(detailPage);
    }

    /**
     * 前端查询个人订单
     *
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDetail>> list(@RequestParam("openid") String openid,
                                            @RequestParam(defaultValue = "0", value = "page") Integer page,
                                            @RequestParam(defaultValue = "10", value = "size") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(openid, pageRequest);
        return ResultVoUtils.success(orderDTOPage.getContent());
    }

    @GetMapping("/detail/{orderId}")
    public ResultVo<OrderDTO> detail(@PathVariable("orderId") String orderId) {

        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVoUtils.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = orderService.cancelOrder(orderId);
        return ResultVoUtils.success();
    }
}
