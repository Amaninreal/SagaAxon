package com.progressivecoder.ordermanagement.orderservice.services.queries;

import com.progressivecoder.ordermanagement.orderservice.dto.queries.OrderResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    @Override
    public OrderResponseDTO getOrderById(String orderId) {
        return new OrderResponseDTO(orderId, "LAPTOP", 2, "CREATED");
    }
}
