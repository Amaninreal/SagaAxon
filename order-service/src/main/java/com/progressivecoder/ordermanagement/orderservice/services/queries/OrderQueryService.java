package com.progressivecoder.ordermanagement.orderservice.services.queries;

import com.progressivecoder.ordermanagement.orderservice.dto.queries.OrderResponseDTO;

import java.util.List;

public interface OrderQueryService {
    OrderResponseDTO getOrderById(String orderId);
    List<OrderResponseDTO> getAllOrders();
}

