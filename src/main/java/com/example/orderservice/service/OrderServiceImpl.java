package com.example.orderservice.service;

import com.example.orderservice.service.model.ItemDto;
import com.example.orderservice.service.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String INVENTORY_SERVICE_URL = "http://inventory-service/items/";
    private final RestTemplate restTemplate;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        String URI = orderRequest.getItemName();

        ResponseEntity<ItemDto> responseEntity = restTemplate.getForEntity(INVENTORY_SERVICE_URL + URI, ItemDto.class);
    }
}
