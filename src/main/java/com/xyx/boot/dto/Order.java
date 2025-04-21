package com.xyx.boot.dto;

import lombok.Data;

/**
 * @description 订单实体类
 * @author lixiang
 */
@Data
public class Order {

    /**
     * 订单金额
     */
    private int amount;

    /**
     * 积分
     */
    private int score;
}
