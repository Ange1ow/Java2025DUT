package com.store.model.order;

import com.store.model.product.Product;
import lombok.Data;

// 1. Узагальнення (Generics): <T>
// 2. Обмеження узагальнень: <T extends Product>
@Data
public class Order<T extends Product> {

    private final String orderId;
    private final T product;
    private final int quantity;

    public Order(String orderId, T product, int quantity) throws InvalidProductException {
        // 3. Обробка виключень
        if (product == null) {
            throw new InvalidProductException("Продукт у замовленні не може бути null.");
        }
        if (quantity <= 0) {
            throw new InvalidProductException("Кількість має бути більше нуля.");
        }
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }
}