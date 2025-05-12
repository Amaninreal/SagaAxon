package com.progressivecoder.shippingmanagement.shippingservice.controllers;

import com.progressivecoder.ecommerce.commands.CreateShippingCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final CommandGateway commandGateway;
    @Autowired
    public ShippingController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createShipping(@RequestBody CreateShippingCommand createShippingCommand) {
        commandGateway.send(createShippingCommand);
        return "Shipping created";
    }
}
