package com.store.app;

import com.github.javafaker.Faker;
import com.store.model.order.InvalidProductException;
import com.store.model.order.Order;
import com.store.model.product.Clothing;
import com.store.model.product.Electronics;
import com.store.model.product.Product;
import com.store.processing.OrderService;
import com.store.storage.OrderStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    private static final Faker FAKER = new Faker(new Locale("uk"));

    public static void main(String[] args) throws InterruptedException {
        // 1. Ініціалізація сервісів з різних модулів
        OrderStorage storage = new OrderStorage();
        OrderService service = new OrderService(5); // Пул з 5 потоків

        List<Order<? extends Product>> submittedOrders = new ArrayList<>();

        System.out.println("--- Симуляція створення та відправки замовлень ---");

        try {
            // 2. Використання JavaFaker та Lombok @Builder
            Electronics tv = Electronics.builder()
                    .id(FAKER.code().ean8())
                    .name("Samsung 8K QLED TV")
                    .price(FAKER.number().randomDouble(2, 1500, 3000))
                    .brand("Samsung")
                    .warrantyMonths(24)
                    .build();

            // 3. Використання узагальнень та обробка виключень
            Order<Electronics> tvOrder = new Order<>(FAKER.code().asin(), tv, 1);
            service.submitOrder(tvOrder, storage); // Відправка в інший потік
            submittedOrders.add(tvOrder);

            Clothing shirt = Clothing.builder()
                    .id(FAKER.code().ean8())
                    .name("Лляна сорочка")
                    .price(FAKER.number().randomDouble(2, 40, 100))
                    .size("L")
                    .material("Льон")
                    .build();

            Order<Clothing> shirtOrder = new Order<>(FAKER.code().asin(), shirt, 2);
            service.submitOrder(shirtOrder, storage);
            submittedOrders.add(shirtOrder);

            // Ще одне замовлення на електроніку
            Electronics phone = Electronics.builder()
                    .id(FAKER.code().ean8())
                    .name("Google Pixel")
                    .price(FAKER.number().randomDouble(2, 800, 1200))
                    .brand("Google")
                    .warrantyMonths(12)
                    .build();

            Order<Electronics> phoneOrder = new Order<>(FAKER.code().asin(), phone, 1);
            service.submitOrder(phoneOrder, storage);
            submittedOrders.add(phoneOrder);

        } catch (InvalidProductException e) {
            System.err.println("Не вдалося створити замовлення: " + e.getMessage());
        }

        // Даємо потокам час на обробку
        Thread.sleep(3000); //

        // 4. Використання Лямбда-виразу
        System.out.println("\n--- Фільтрація замовлень (Лямбда) ---");
        System.out.println("Тільки замовлення електроніки:");

        List<Order<?>> electronicsOrders = submittedOrders.stream()
                .filter(order -> order.getProduct() instanceof Electronics)
                .toList();

        // 5. Використання Посилання на метод (Method Reference)
        // Ми передаємо System.out::println як реалізацію інтерфейсу Consumer
        electronicsOrders.forEach(System.out::println);

        // 6. Зупинка сервісу
        service.shutdown();

        // 7. Перевірка результату в сховищі
        storage.printAllOrders();
    }
}