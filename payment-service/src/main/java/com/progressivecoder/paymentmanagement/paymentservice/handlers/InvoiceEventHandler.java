package com.progressivecoder.paymentmanagement.paymentservice.handlers;
import com.progressivecoder.ecommerce.events.InvoiceCreatedEvent;
import com.progressivecoder.paymentmanagement.paymentservice.aggregates.InvoiceStatus;
import com.progressivecoder.paymentmanagement.paymentservice.entity.Invoice;
import com.progressivecoder.paymentmanagement.paymentservice.repository.InvoiceRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class InvoiceEventHandler {

    private final InvoiceRepository invoiceRepository;

    public InvoiceEventHandler(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @EventHandler
    public void on(InvoiceCreatedEvent event) {
        Invoice invoice = new Invoice(event.getPaymentId(), event.getOrderId(), InvoiceStatus.PAID);
        invoiceRepository.save(invoice);
    }
}
