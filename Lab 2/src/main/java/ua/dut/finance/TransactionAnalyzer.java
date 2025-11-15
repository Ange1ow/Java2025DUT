package ua.dut.finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    // Форматер для дати
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // [cite: 154]

    // Приватний конструктор
    private TransactionAnalyzer() {}

    //Розрахунок загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
    //Підрахунок транзакцій за місяць
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    //Завдання 5: Аналіз та виведення 10 найбільших витрат

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }
    //Визначення найбільшої витрати за місяць
    public static Transaction findMaxExpenseByMonth(List<Transaction> transactions, String monthYear) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Тільки витрати
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                    return date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear);
                })
                .min(Comparator.comparing(Transaction::getAmount)) // min, тому що суми від'ємні (-1000 < -100)
                .orElse(null);
    }
    //Визначення найменшої витрати за місяць
    public static Transaction findMinExpenseByMonth(List<Transaction> transactions, String monthYear) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Тільки витрати
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                    return date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear);
                })
                .max(Comparator.comparing(Transaction::getAmount)) // max, тому що суми від'ємні (-100 > -1000)
                .orElse(null);
    }
    //[cite_start]Самостійна робота: Підсумок витрат по категоріях
    public static java.util.Map<String, Double> summarizeExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Тільки витрати
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }
    //Підсумок витрат по місяцях
    public static java.util.Map<String, Double> summarizeExpensesByMonth(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Тільки витрати
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), dateFormatter).format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }
}