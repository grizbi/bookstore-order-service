package com.example.orderservice.service;

import com.example.orderservice.repository.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.model.ItemDto;
import com.example.orderservice.service.model.OrderRequest;
import com.example.orderservice.service.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String INVENTORY_SERVICE_URL = "http://inventory-service/items/";
    private static final String PAYMENT_SERVICE_URL = "http://payment-service/payment/";
    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        String URI = orderRequest.getItemName();

        ResponseEntity<ItemDto> itemDataResponse = restTemplate.getForEntity(INVENTORY_SERVICE_URL + URI, ItemDto.class);

        PaymentRequest paymentRequest = PaymentRequest.builder().cost(
                Objects.requireNonNull(itemDataResponse.getBody()).getPrice()).userId(orderRequest.getUserId()).build();
        ResponseEntity<Boolean> paymentResponse = restTemplate.postForEntity(PAYMENT_SERVICE_URL, paymentRequest, Boolean.class);

        if (paymentResponse.getBody() != null && !paymentResponse.getBody()) {
            log.error("Could not place the order, you do not have enough money");
            return null;
        }

        Order createdOrder = Order.builder().price(itemDataResponse.getBody().getPrice())
                .itemName(orderRequest.getItemName())
                .userId(orderRequest.getUserId())
                .submissionDate(LocalDateTime.now())
                .build();

        orderRepository.save(createdOrder);
        log.info("Order has been placed");
        return createdOrder;
    }
}
