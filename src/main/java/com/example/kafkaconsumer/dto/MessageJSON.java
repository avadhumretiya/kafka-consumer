package com.example.kafkaconsumer.dto;

import lombok.Data;

@Data
public class MessageJSON {

    private String name;
    private Integer price;
    private String company;
    private Integer quantity;
    private Boolean inStock;
}
