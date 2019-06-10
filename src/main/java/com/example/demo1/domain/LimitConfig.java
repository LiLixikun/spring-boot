package com.example.demo1.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LimitConfig {
    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getDescriptios() {
        return descriptios;
    }

    public void setDescriptios(String descriptios) {
        this.descriptios = descriptios;
    }

    private BigDecimal minMoney;

    private BigDecimal maxMoney;

    private String descriptios;
}
