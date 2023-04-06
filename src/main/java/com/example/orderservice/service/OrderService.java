package com.example.orderservice.service;

import com.example.orderservice.repository.Order;
import com.example.orderservice.service.model.OrderRequest;

public interface OrderService {

    /**
     * Places a new order.
     *
     * @param orderRequest orderRequest
     */
    Order placeOrder(OrderRequest orderRequest);

}
