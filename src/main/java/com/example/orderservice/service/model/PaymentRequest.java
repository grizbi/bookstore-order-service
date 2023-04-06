package com.example.orderservice.service.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentRequest {
    private int userId;
    private int cost;

}
