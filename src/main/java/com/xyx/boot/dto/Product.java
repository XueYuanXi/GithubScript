package com.xyx.boot.dto;

import java.io.Serializable;

public class Product implements Serializable {
    /**
     * 钻石
     */
    public static final String DIAMOND = "0";
    /**
     * 黄金
     */
    public static final String GOLD = "1";
    /**
     * 类型
     */
    private String type;
    /**
     * 折扣
     */
    private int discount;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}

