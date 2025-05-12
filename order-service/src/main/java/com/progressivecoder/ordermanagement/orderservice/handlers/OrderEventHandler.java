package com.progressivecoder.ordermanagement.orderservice.handlers;

import com.progressivecoder.ordermanagement.orderservice.aggregates.ItemType;
import com.progressivecoder.ordermanagement.orderservice.aggregates.OrderStatus;
import com.progressivecoder.ordermanagement.orderservice.entity.Order;
import com.progressivecoder.ordermanagement.orderservice.repository.OrderRepository;
import com.progressivecoder.ecommerce.events.OrderCreatedEvent;
import com.progressivecoder.ecommerce.events.OrderUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        //persist the order when it's created by the aggregate
        Order order = new Order(
                event.getOrderId(),
                ItemType.valueOf(event.getItemType()),
                event.getPrice(),
                event.getCurrency(),
                OrderStatus.valueOf(event.getOrderStatus())
        );
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderUpdatedEvent event) {
        //fetch order by its orderId
        Order order = orderRepository.findByOrderId(event.getOrderId());

        if (order != null) {
            order.setOrderStatus(OrderStatus.valueOf(event.getOrderStatus()));
            orderRepository.save(order);
        }
    }
}
