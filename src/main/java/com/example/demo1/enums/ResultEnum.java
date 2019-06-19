package com.example.demo1.enums;

public enum ResultEnum {

    SUCCESS(200, "成功"),
    PRODUCT_NO_EXIT(-100,"商品不存在"),
    PRODUCT_DOWN(-101,"商品已经下架"),
    PRODUCT_STOCK_ERR(-102,"库存不足"),
    CART_NOT_NULL(-103,"购物车不能为空"),
    PRODUCT_ORDER_EMPTY(-150,"订单不存在"),
    MENU_NO_EXIT(-151,"菜单不存在"),
    ORDER_NO_CANCEL(-160,"当前订单无法取消"),
    ORDER_DETAIL_EMPTY(-161,"订单无商品信息"),
    ORDER_CANCEL_FAIL(-162,"取消订单失败"),
    ORDER_STSTUS_ERR(-110,"订单状态不正确"),
    ODER_FINISH_FAIL(-163,"订单已完结"),
    ORDER_PAY_FAIL(-164,"订单支付失败"),
    ORDER_CREAT_FAIL(-165,"订单创建失败"),
    FORM_ERR(-120,"参数不正确"),
    FAIL(-1, "失败"),
    WEIXIN_GETTOKEN_ERR(-180,"获取微信token失败"),
    WEIXIN_GET_USERINFO_ERR(-181,"获取微信用户信息失败"),
    UNLOGIN(-2, "未登陆");


    private Integer code;

    private String message;

    private ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
