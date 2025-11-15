package com.store.processing;

import com.store.model.order.Order;
import com.store.model.product.Product;
import com.store.storage.OrderStorage;

// Цей Runnable є внутрішньою деталлю реалізації
class OrderProcessorTask implements Runnable {

    private final Order<? extends Product> order;
    private final OrderStorage storage;

    public OrderProcessorTask(Order<? extends Product> order, OrderStorage storage) {
        this.order = order;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            System.out.printf("[Thread %d] 1. Початок обробки замовлення: %s\n",
                    Thread.currentThread().getId(), order.getOrderId());

            // Симуляція складної роботи (наприклад, перевірка складу, оплата)
            Thread.sleep((long) (Math.random() * 1000 + 500));

            // Збереження в сховище
            storage.addOrder(order);

            System.out.printf("[Thread %d] 2. Завершено обробку: %s\n",
                    Thread.currentThread().getId(), order.getOrderId());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("[Thread %d] Помилка обробки: %s\n",
                    Thread.currentThread().getId(), e.getMessage());
        }
    }
}