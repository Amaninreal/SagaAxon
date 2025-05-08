package com.progressivecoder.ecommerce.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.math.BigDecimal;

public class CreateOrderCommand {

    @TargetAggregateIdentifier
    public final String orderId;
    public final String itemType;
    public final BigDecimal price;
    public final String currency;
    public final String orderStatus;

    // Required no-arg constructor
    public CreateOrderCommand() {
        this.orderId = null;
        this.itemType = null;
        this.price = null;
        this.currency = null;
        this.orderStatus = null;
    }

    public CreateOrderCommand(String orderId, String itemType,
                              BigDecimal price, String currency,
                              String orderStatus) {
        this.orderId = orderId;
        this.itemType = itemType;
        this.price = price;
        this.currency = currency;
        this.orderStatus = orderStatus;
    }
}