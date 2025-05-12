package com.progressivecoder.paymentmanagement.paymentservice.entity;

import com.progressivecoder.paymentmanagement.paymentservice.aggregates.InvoiceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Invoice {

    @Id
    private String paymentId;

    private String orderId;

    private InvoiceStatus status;

    public Invoice() {}

    public Invoice(String paymentId, String orderId, InvoiceStatus status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.status = status;
    }
}
