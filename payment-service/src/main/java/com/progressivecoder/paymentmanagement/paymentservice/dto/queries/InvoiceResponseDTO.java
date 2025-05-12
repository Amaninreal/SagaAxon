package com.progressivecoder.paymentmanagement.paymentservice.dto.queries;

public class InvoiceResponseDTO {
    private String paymentId;
    private String orderId;
    private String status;

    public InvoiceResponseDTO() {}

    public InvoiceResponseDTO(String paymentId, String orderId, String status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
