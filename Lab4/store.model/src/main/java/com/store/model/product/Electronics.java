package com.store.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Electronics extends Product {
    private String brand;
    private int warrantyMonths;
}