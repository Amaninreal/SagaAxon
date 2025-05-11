package com.progressivecoder.ordermanagement.orderservice.services.queries;

import com.progressivecoder.ordermanagement.orderservice.dto.queries.OrderResponseDTO;

public interface OrderQueryService {
    OrderResponseDTO getOrderById(String orderId);
}

