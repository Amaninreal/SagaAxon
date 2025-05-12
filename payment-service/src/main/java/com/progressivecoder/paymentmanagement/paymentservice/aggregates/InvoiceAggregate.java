package com.progressivecoder.paymentmanagement.paymentservice.aggregates;

import com.progressivecoder.ecommerce.commands.CreateInvoiceCommand;
import com.progressivecoder.ecommerce.events.InvoiceCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class InvoiceAggregate {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceAggregate.class);

    @AggregateIdentifier
    private String paymentId;

    private String orderId;

    private InvoiceStatus invoiceStatus;

    public InvoiceAggregate() {
    }

    @CommandHandler
    public InvoiceAggregate(CreateInvoiceCommand createInvoiceCommand){
        AggregateLifecycle.apply(new InvoiceCreatedEvent(createInvoiceCommand.paymentId, createInvoiceCommand.orderId));
        logger.info("Published InvoiceCreatedEvent with paymentId: {} and orderId: {}", createInvoiceCommand.paymentId, createInvoiceCommand.orderId);
    }

    @EventSourcingHandler
    protected void on(InvoiceCreatedEvent invoiceCreatedEvent){
        this.paymentId = invoiceCreatedEvent.paymentId;
        this.orderId = invoiceCreatedEvent.orderId;
        this.invoiceStatus = InvoiceStatus.PAID;
        logger.info("Event sourced: InvoiceCreatedEvent for paymentId: {} and orderId: {}", invoiceCreatedEvent.paymentId, invoiceCreatedEvent.orderId);
    }
}
