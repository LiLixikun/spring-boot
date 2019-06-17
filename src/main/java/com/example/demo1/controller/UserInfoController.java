package com.example.demo1.controller;

import com.example.demo1.dataobject.UserInfo;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(@RequestParam("openId") String openId)throws SellException{
        UserInfo userInfo=userInfoRepository.findById(openId).get();
        if(userInfo==null){
            throw new SellException(ResultEnum.WEIXIN_GET_USERINFO_ERR);
        }
        return userInfo;
    }
}
