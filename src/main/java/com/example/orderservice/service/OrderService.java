package com.example.orderservice.service;

import com.example.orderservice.service.model.OrderRequest;

public interface OrderService {

    /**
     * Places a new order.
     *
     * @param orderRequest orderRequest
     */
    void placeOrder(OrderRequest orderRequest);

}
