package com.progressivecoder.ordermanagement.orderservice.sagas;

import com.progressivecoder.ecommerce.commands.CreateInvoiceCommand;
import com.progressivecoder.ecommerce.commands.CreateShippingCommand;
import com.progressivecoder.ecommerce.commands.UpdateOrderStatusCommand;
import com.progressivecoder.ecommerce.events.*;
import com.progressivecoder.ordermanagement.orderservice.aggregates.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
public class OrderManagementSaga {
    private static final Logger logger = LoggerFactory.getLogger(OrderManagementSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent){
        String paymentId = UUID.randomUUID().toString();
        logger.info("Saga invoked for OrderCreatedEvent with orderId: {}", orderCreatedEvent.orderId);

        //associate Saga
        SagaLifecycle.associateWith("paymentId", paymentId);
        logger.info("Generated paymentId: {}", paymentId);

        System.out.println("order id" + orderCreatedEvent.orderId);

        //send the commands
        commandGateway.send(new CreateInvoiceCommand(paymentId, orderCreatedEvent.orderId));
        logger.info("Sent CreateInvoiceCommand with paymentId: {} and orderId: {}", paymentId, orderCreatedEvent.orderId);
    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
        String shippingId = UUID.randomUUID().toString();
        logger.info("Saga continued for InvoiceCreatedEvent with paymentId: {}", invoiceCreatedEvent.paymentId);

        System.out.println("Saga continued");

        //associate Saga with shipping
        SagaLifecycle.associateWith("shipping", shippingId);
        logger.info("Generated shippingId: {}", shippingId);

        //send the create shipping command
        commandGateway.send(new CreateShippingCommand(shippingId, invoiceCreatedEvent.orderId, invoiceCreatedEvent.paymentId));
        logger.info("Sent CreateShippingCommand with shippingId: {}, orderId: {}, paymentId: {}", shippingId, invoiceCreatedEvent.orderId, invoiceCreatedEvent.paymentId);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent orderShippedEvent){
        logger.info("Handling OrderShippedEvent for orderId: {}", orderShippedEvent.getOrderId());
        commandGateway.send(new UpdateOrderStatusCommand(orderShippedEvent.getOrderId(), String.valueOf(OrderStatus.SHIPPED)));
        logger.info("Sent UpdateOrderStatusCommand for orderId: {}", orderShippedEvent.getOrderId());

    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderUpdatedEvent orderUpdatedEvent){
        logger.info("Handling OrderUpdatedEvent for orderId: {}", orderUpdatedEvent.orderId);
        SagaLifecycle.end();
        logger.info("Saga ended for orderId: {}", orderUpdatedEvent.orderId);
    }
}
