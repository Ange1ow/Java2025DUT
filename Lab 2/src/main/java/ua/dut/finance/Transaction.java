package ua.dut.finance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Автоматично створює getters, setters, toString, equals, hashCode
@AllArgsConstructor // Створює конструктор з усіма полями
public class Transaction {
    private String date;
    private double amount;
    private String description;
}