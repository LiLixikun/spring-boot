package com.example.demo1.controller;

import com.example.demo1.config.WeChatAccountConfig;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/weixin")
@RestController
@Slf4j
public class WeixinController {

    @Autowired
    private WeChatAccountConfig weChatConfig;

    @GetMapping("/getCode")
    public void getCode(){
        String authUrl=weChatConfig.getWxAppId();
    }

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code)throws Exception{
        log.info("进入auth 方法");

        log.info("code={}",code);

        String authUrl=weChatConfig.getWxAppId();

        //根据code 换取 access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa830230a1a818a46&secret=6d2141b57fc59362f9f631f89d0243c7&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        if(response==null){
            throw new SellException(ResultEnum.WEIXIN_GETTOKEN_ERR);
        }



        log.info("response={}", response);

        //拉取用户信息
        String userUrl="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    }
}
