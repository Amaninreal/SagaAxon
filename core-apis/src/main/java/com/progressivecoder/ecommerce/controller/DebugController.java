package com.progressivecoder.ecommerce.controller;

import com.progressivecoder.ecommerce.commands.CreateOrderCommand;
import org.axonframework.serialization.Serializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    private final Serializer serializer;

    public DebugController(Serializer serializer) {
        this.serializer = serializer;
    }

    @PostMapping("/debug/order")
    public String debugCommand(@RequestBody CreateOrderCommand command) {
        try {
            String xml = serializer.serialize(command, String.class).getData();
            System.out.println("Serialized command:\n" + xml);
            return xml;
        } catch (Exception e) {
            e.printStackTrace();
            return "Serialization failed: " + e.getMessage();
        }
    }
}