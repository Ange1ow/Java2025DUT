package com.store.storage;

import com.store.model.order.Order;
import com.store.model.product.Product;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Потоко-безпечне сховище
public class OrderStorage {

    // Використовуємо ConcurrentHashMap для потоко-безпечності
    // Зберігаємо замовлення з будь-яким типом, що є підтипом Product
    private final Map<String, Order<? extends Product>> database = new ConcurrentHashMap<>();

    // Узагальнений метод для додавання
    public <T extends Product> void addOrder(Order<T> order) {
        database.put(order.getOrderId(), order);
        System.out.printf("[Storage] Збережено замовлення: %s (Продукт: %s)\n",
                order.getOrderId(), order.getProduct().getName());
    }

    public void printAllOrders() {
        System.out.println("\n--- Всі збережені замовлення ---");
        database.values().forEach(order ->
                System.out.printf("ID: %s, Тип: %s\n",
                        order.getOrderId(), order.getProduct().getClass().getSimpleName())
        );
    }
}