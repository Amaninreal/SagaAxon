package com.progressivecoder.paymentmanagement.paymentservice.services.commands;
import com.progressivecoder.paymentmanagement.paymentservice.dto.commands.CreateInvoiceDTO;
import com.progressivecoder.paymentmanagement.paymentservice.entity.Invoice;
import com.progressivecoder.paymentmanagement.paymentservice.aggregates.InvoiceStatus;  // Import the enum from aggregates package
import com.progressivecoder.paymentmanagement.paymentservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(CreateInvoiceDTO createInvoiceDTO) {
        String paymentId = UUID.randomUUID().toString();

        Invoice invoice = new Invoice(paymentId, createInvoiceDTO.getOrderId(), InvoiceStatus.PAID);
        return invoiceRepository.save(invoice);
    }
}

