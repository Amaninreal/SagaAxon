package com.progressivecoder.paymentmanagement.paymentservice.repository;

import com.progressivecoder.paymentmanagement.paymentservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Invoice findByOrderId(String orderId);
}
