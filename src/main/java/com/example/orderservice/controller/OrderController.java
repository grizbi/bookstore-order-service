package com.example.orderservice.controller;

import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    void placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
    }
}
