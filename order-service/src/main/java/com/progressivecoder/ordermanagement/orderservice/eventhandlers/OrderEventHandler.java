package com.progressivecoder.ordermanagement.orderservice.eventhandlers;

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
        // Fetch the existing order by its orderId
        Order order = orderRepository.findByOrderId(event.getOrderId());

        if (order != null) {
            // If order exists, update its status
            order.setOrderStatus(OrderStatus.valueOf(event.getOrderStatus()));  // Update status
            orderRepository.save(order);  // Save the updated order to the database
        }
    }
}
