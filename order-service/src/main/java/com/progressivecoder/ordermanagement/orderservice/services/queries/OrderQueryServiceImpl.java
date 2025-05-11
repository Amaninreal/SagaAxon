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
            return mapToOrderResponseDTO(order);  // Use the helper method for mapping
        } else {
            throw new OrderNotFoundException("Order not found for id: " + orderId);
        }
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll(); // Fetch all orders from the database

        // Use the helper method for each order in the list
        return orders.stream()
                .map(this::mapToOrderResponseDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert Order to OrderResponseDTO
    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getOrderId(),
                order.getItemType().name(),   // Enum to String conversion
                order.getPrice().intValue(),  // Assuming quantity can be calculated from price
                order.getOrderStatus().name() // Enum to String conversion
        );
    }
}
