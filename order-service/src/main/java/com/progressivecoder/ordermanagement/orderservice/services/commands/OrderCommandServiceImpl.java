package com.progressivecoder.ordermanagement.orderservice.services.commands;

import com.progressivecoder.aws.services.S3Service;
import com.progressivecoder.ordermanagement.orderservice.aggregates.OrderStatus;
import com.progressivecoder.ecommerce.commands.CreateOrderCommand;
import com.progressivecoder.ordermanagement.orderservice.dto.commands.OrderCreateDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final CommandGateway commandGateway;
    private final S3Service s3Service;

    public OrderCommandServiceImpl(CommandGateway commandGateway, S3Service s3service) {
        this.commandGateway = commandGateway;
        this.s3Service = s3service;
    }

    @Override
    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
        String orderId = UUID.randomUUID().toString();

        // Absolute path to the file
        Path dummyFilePath = Paths.get("/home/nashtech/Downloads/awss3sqs.csv");

        // S3 file path
        String fileKey = "orders/" + orderId + ".csv";

        try {
            // Pass the correct Path to the upload method
            s3Service.uploadFile(fileKey, dummyFilePath);
        } catch (Exception e) {
            System.err.println("S3 Upload failed: " + e.getMessage());
        }

        return commandGateway.send(new CreateOrderCommand(orderId,
                orderCreateDTO.getItemType(),
                orderCreateDTO.getPrice(),
                orderCreateDTO.getCurrency(),
                String.valueOf(OrderStatus.CREATED)));
    }
}
