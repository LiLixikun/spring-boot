package com.example.demo1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    /**
     * 微信公账号
     */
    private String wxAppId;

    /**
     * 微信公账号秘钥
     */
    private String wxAppSecret;

    /**
     * 微信开发平台账号
     */
    private String openAppId;

    /**
     * 微信开发平台秘钥
     */
    private String openAppSecret;

    private String baseHttp;
}
