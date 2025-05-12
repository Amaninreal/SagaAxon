package com.progressivecoder.ecommerce.events;

public class OrderShippedEvent {

    private final String shippingId;

    private final String orderId;

    private final String paymentId;

    public OrderShippedEvent(String shippingId, String orderId, String paymentId) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public String getShippingId() {
        return shippingId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }
}
