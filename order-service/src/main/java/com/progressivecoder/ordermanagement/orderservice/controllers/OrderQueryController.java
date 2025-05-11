package com.progressivecoder.ordermanagement.orderservice.controllers;

import com.progressivecoder.ordermanagement.orderservice.dto.queries.OrderResponseDTO;
import com.progressivecoder.ordermanagement.orderservice.services.queries.OrderQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Queries", description = "Order Queries Related Endpoints")
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    public OrderQueryController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable String orderId) {
        return orderQueryService.getOrderById(orderId);
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderQueryService.getAllOrders();  // Endpoint for fetching all orders
    }
}
