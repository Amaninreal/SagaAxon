package com.progressivecoder.ordermanagement.orderservice.entity;

import com.progressivecoder.ordermanagement.orderservice.aggregates.ItemType;
import com.progressivecoder.ordermanagement.orderservice.aggregates.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private BigDecimal price;

    private String currency;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public Order() {
    }

    public Order(String orderId, ItemType itemType, BigDecimal price, String currency, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.itemType = itemType;
        this.price = price;
        this.currency = currency;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
