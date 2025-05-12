package com.progressivecoder.paymentmanagement.paymentservice.dto.commands;

public class CreateInvoiceDTO {
    private String orderId;

    public CreateInvoiceDTO() {}

    public CreateInvoiceDTO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
