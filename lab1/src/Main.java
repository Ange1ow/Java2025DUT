import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- Створення тестових даних ---
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук", electronics));
        allProducts.add(new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном", smartphones));
        allProducts.add(new Product(3, "Навушники", 2499.00, "Бездротові навушники", accessories));

        // --- Ініціалізація основних об'єктів ---
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>(); // Для історії замовлень

        // --- Головний цикл програми ---
        while (true) {
            System.out.println("\nМЕНЮ МАГАЗИНУ");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару за назвою");
            System.out.println("0 - Вийти");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера після nextInt()

            switch (choice) {
                case 1:
                    System.out.println("\nСписок доступних товарів");
                    for (Product p : allProducts) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.print("Введіть ID товару для додавання: ");
                    int addId = scanner.nextInt();
                    Product productToAdd = null;
                    for (Product p : allProducts) {
                        if (p.getId() == addId) {
                            productToAdd = p;
                            break;
                        }
                    }
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар '" + productToAdd.getName() + "' додано до кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;
                case 3:
                    System.out.println("\n" + cart);
                    break;
                case 4: // Самостійна робота: Видалення товару
                    System.out.print("Введіть ID товару для видалення з кошика: ");
                    int removeId = scanner.nextInt();
                    Product productToRemove = null;
                    for (Product p : cart.getProducts()) {
                        if (p.getId() == removeId) {
                            productToRemove = p;
                            break;
                        }
                    }
                    if (productToRemove != null) {
                        cart.removeProduct(productToRemove);
                        System.out.println("Товар '" + productToRemove.getName() + "' видалено з кошика.");
                    } else {
                        System.out.println("Товар з таким ID у кошику не знайдено.");
                    }
                    break;
                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Спочатку додайте товари.");
                    } else {
                        Order newOrder = new Order(cart);
                        orderHistory.add(newOrder); // Зберігаємо замовлення в історію
                        System.out.println("\nЗамовлення успішно оформлено!");
                        System.out.println(newOrder);
                        cart.clear();
                    }
                    break;
                case 6: // Самостійна робота: Історія замовлень
                    if (orderHistory.isEmpty()) {
                        System.out.println("\nІсторія замовлень порожня.");
                    } else {
                        System.out.println("\nВаша історія замовлень");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("\n*** Замовлення #" + (i + 1) + " ***");
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;
                case 7: // Самостійна робота: Пошук товару
                    System.out.print("Введіть назву товару для пошуку: ");
                    String query = scanner.nextLine().toLowerCase();
                    System.out.println("\nРезультати пошуку");
                    boolean found = false;
                    for (Product p : allProducts) {
                        if (p.getName().toLowerCase().contains(query)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Товарів за вашим запитом не знайдено.");
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин! До побачення!");
                    return;
                default:
                    System.out.println("Невідома опція. Будь ласка, спробуйте ще раз.");
                    break;
            }
        }
    }
}