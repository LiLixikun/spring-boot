package com.example.demo1.dataobject;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;

@Data
@Entity
public class UserInfo {

    @Id
    private String openId;

    private String nickname;

    private String sex;

    private String province;

    private String country;

    private String headImgUrl;
}
