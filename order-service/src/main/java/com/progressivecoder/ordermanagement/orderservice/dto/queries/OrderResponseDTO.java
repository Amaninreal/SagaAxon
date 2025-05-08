package com.progressivecoder.ordermanagement.orderservice.dto.queries;

public class OrderResponseDTO {
    private String orderId;
    private String itemType;
    private int quantity;
    private String status;

    public OrderResponseDTO() {

    }

    public OrderResponseDTO(String orderId, String itemType, int quantity, String status) {
        this.orderId = orderId;
        this.itemType = itemType;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItemType() {
        return itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
