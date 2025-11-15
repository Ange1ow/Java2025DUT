package com.store.model.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder // Дозволяє @Builder працювати з успадкуванням
@NoArgsConstructor // Потрібен для SuperBuilder
public abstract class Product {
    private String id;
    private double price;
    private String name;
}