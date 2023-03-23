package com.example.orderservice.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private LocalDateTime submissionDate;
    private long userId;
    private int price;
    private int requestedItemId;

}
