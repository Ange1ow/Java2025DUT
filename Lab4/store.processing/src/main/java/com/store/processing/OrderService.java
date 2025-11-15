package com.store.processing;

import com.store.model.order.Order;
import com.store.model.product.Product;
import com.store.storage.OrderStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderService {

    // Створюємо пул потоків для обробки замовлень
    private final ExecutorService executor;

    public OrderService(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
        System.out.printf("[Service] Запущено OrderService з %d потоками.\n", poolSize);
    }

    // Узагальнений метод для прийому замовлень
    public <T extends Product> void submitOrder(Order<T> order, OrderStorage storage) {
        System.out.printf("[Service] Отримано нове замовлення: %s\n", order.getOrderId());
        // Створюємо нове завдання та передаємо його в пул потоків
        executor.submit(new OrderProcessorTask(order, storage));
    }

    // Метод для коректного завершення роботи
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("[Service] OrderService зупинено.");
    }
}