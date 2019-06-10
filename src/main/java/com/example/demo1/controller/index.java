package com.example.demo1.controller;

import com.example.demo1.domain.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/text")
public class index {

    @Autowired
    private LimitConfig limitConfig;

    //不用带参数名直接获取参数
    @GetMapping("/say/{id}")
    public String say(@PathVariable(name = "id", required = false) Integer id) {
        return "id=" + id;
    }

    //通过RequestParam 解析url的参数
    @GetMapping("/index")
    public String index(@RequestParam(value = "id", required = false, defaultValue = "50") Integer id) {
        String hello = "hello2";
        return hello + limitConfig.getMaxMoney() + "id=" + id;
    }

    @PostMapping("/getList")
    public LimitConfig getList(@RequestBody LimitConfig limt) {
        String data = "getDescriptios:" + limt.getDescriptios();
        return limt;
    }
}
