package ua.dut.finance;

import java.time.LocalDate; // [cite: 173]
import java.time.format.DateTimeFormatter; // [cite: 174]
import java.util.Comparator; // [cite: 237]
import java.util.List; // [cite: 172]
import java.util.stream.Collectors; // [cite: 239]

// Вимога 4: Абстрактний клас
public abstract class TransactionAnalyzer {

    // Форматер для дати [cite: 154]
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // [cite: 154]

    // Вимога 4: Приватний конструктор
    private TransactionAnalyzer() {}

    /**
     * Завдання 2: Розрахунок загального балансу [cite: 88]
     */
    public static double calculateTotalBalance(List<Transaction> transactions) { // [cite: 96]
        double balance = 0; // [cite: 97]
        for (Transaction transaction : transactions) { // [cite: 98]
            balance += transaction.getAmount(); // [cite: 99]
        }
        return balance; // [cite: 101]
    }

    /**
     * Завдання 3: Підрахунок транзакцій за місяць [cite: 148]
     */
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) { // [cite: 157]
        int count = 0; // [cite: 158]
        for (Transaction transaction : transactions) { // [cite: 159]
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter); // [cite: 160]
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy")); // [cite: 161]
            if (transactionMonthYear.equals(monthYear)) { // [cite: 162]
                count++; // [cite: 163]
            }
        }
        return count; // [cite: 166]
    }

    /**
     * Завдання 5: Аналіз та виведення 10 найбільших витрат [cite: 236]
     */
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) { // [cite: 243]
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // [cite: 245]
                .sorted(Comparator.comparing(Transaction::getAmount)) // [cite: 246]
                .limit(10) // [cite: 247]
                .collect(Collectors.toList()); // [cite: 248]
    }
    /**
     * [cite_start]Самостійна робота: Визначення найбільшої витрати за місяць [cite: 283]
     */
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

    /**
     * [cite_start]Самостійна робота: Визначення найменшої витрати за місяць [cite: 283]
     */
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

    /**
     * [cite_start]Самостійна робота: Підсумок витрат по категоріях [cite: 284]
     */
    public static java.util.Map<String, Double> summarizeExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Тільки витрати
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    /**
     * [cite_start]Самостійна робота: Підсумок витрат по місяцях [cite: 284]
     */
    public static java.util.Map<String, Double> summarizeExpensesByMonth(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Тільки витрати
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), dateFormatter).format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }
}