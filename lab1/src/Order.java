import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private List<Product> products;
    private double totalPrice;
    private String status;

    public Order(Cart cart) {
        // Копіюємо товари, щоб замовлення не змінилось, якщо кошик зміниться
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("--- Деталі замовлення ---\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Загальна вартість: ").append(String.format("%.2f", totalPrice)).append("\n");
        sb.append("Статус: ").append(status);
        return sb.toString();
    }
}