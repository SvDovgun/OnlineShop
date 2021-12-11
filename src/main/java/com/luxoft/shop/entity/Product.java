package com.luxoft.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
public class Product {
    private int id;
    private String name;
    private double price;
    private String notes;
    private Timestamp creationDate;
}
