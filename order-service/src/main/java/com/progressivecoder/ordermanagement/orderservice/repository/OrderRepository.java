package com.progressivecoder.ordermanagement.orderservice.repository;


import com.progressivecoder.ordermanagement.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    // Custom query to find Order by orderId (if necessary)
    Order findByOrderId(String orderId);
}

