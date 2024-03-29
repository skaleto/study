package com.skaleto.things.java100.java8.stream.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//订单类
@Data
public class Order {
    private Long id;
    private Long customerId;//顾客ID
    private String customerName;//顾客姓名
    private List<OrderItem> orderItemList;//订单商品明细
    private Double totalPrice;//总价格
    private LocalDateTime placedAt;//下单时间
}