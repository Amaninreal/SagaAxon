package com.progressivecoder.paymentmanagement.paymentservice.controller;

import com.progressivecoder.paymentmanagement.paymentservice.dto.commands.CreateInvoiceDTO;
import com.progressivecoder.paymentmanagement.paymentservice.entity.Invoice;
import com.progressivecoder.paymentmanagement.paymentservice.services.commands.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody CreateInvoiceDTO createInvoiceDTO) {
        return invoiceService.createInvoice(createInvoiceDTO);
    }

}
