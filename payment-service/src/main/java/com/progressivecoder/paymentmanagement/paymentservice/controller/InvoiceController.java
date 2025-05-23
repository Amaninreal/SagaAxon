package com.progressivecoder.paymentmanagement.paymentservice.controller;

import com.progressivecoder.paymentmanagement.paymentservice.dto.commands.CreateInvoiceDTO;
import com.progressivecoder.paymentmanagement.paymentservice.dto.queries.InvoiceResponseDTO;
import com.progressivecoder.paymentmanagement.paymentservice.entity.Invoice;
import com.progressivecoder.paymentmanagement.paymentservice.services.commands.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody CreateInvoiceDTO createInvoiceDTO) {
        Invoice invoice = invoiceService.createInvoice(createInvoiceDTO);
        InvoiceResponseDTO responseDTO = new InvoiceResponseDTO(
                invoice.getPaymentId(),
                invoice.getOrderId(),
                invoice.getStatus().name()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


}
