package com.progressivecoder.shippingmanagement.shippingservice.aggregates;

import com.progressivecoder.ecommerce.commands.CreateShippingCommand;
import com.progressivecoder.ecommerce.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class ShippingAggregate {

    private static final Logger logger = LoggerFactory.getLogger(ShippingAggregate.class);

    @AggregateIdentifier
    private String shippingId;

    private String orderId;

    private String paymentId;

    public ShippingAggregate() {
    }

    @CommandHandler
    public ShippingAggregate(CreateShippingCommand createShippingCommand){
        logger.info("Received CreateShippingCommand for orderId: {}", createShippingCommand.getOrderId());
        AggregateLifecycle.apply(new OrderShippedEvent(
                createShippingCommand.getShippingId(),
                createShippingCommand.getOrderId(),
                createShippingCommand.getPaymentId()
        ));
    }

    @EventSourcingHandler
    protected void on(OrderShippedEvent orderShippedEvent){
        logger.info("Applied OrderShippedEvent for shippingId: {}", orderShippedEvent.getShippingId());
        this.shippingId = orderShippedEvent.getShippingId();
        this.orderId = orderShippedEvent.getOrderId();
        this.paymentId = orderShippedEvent.getPaymentId();
    }
}
