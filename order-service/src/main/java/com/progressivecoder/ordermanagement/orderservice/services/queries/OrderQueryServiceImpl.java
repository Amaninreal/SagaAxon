package com.progressivecoder.ordermanagement.orderservice.services.queries;

import com.progressivecoder.ordermanagement.orderservice.dto.queries.OrderResponseDTO;
import com.progressivecoder.ordermanagement.orderservice.entity.Order;
import com.progressivecoder.ordermanagement.orderservice.exceptions.OrderNotFoundException;
import com.progressivecoder.ordermanagement.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;

    public OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDTO getOrderById(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        if (order != null) {
            return mapToOrderResponseDTO(order);
        } else {
            throw new OrderNotFoundException("Order not found for id: " + orderId);
        }
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToOrderResponseDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getOrderId(),
                order.getItemType().name(),
                order.getPrice().intValue(),
                order.getOrderStatus().name()
        );
    }
}
