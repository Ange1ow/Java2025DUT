package ua.dut.finance;

import java.util.List;

// Вимога 4: Абстрактний клас
public abstract class TransactionReportGenerator {

    // Вимога 4: Приватний конструктор
    private TransactionReportGenerator() {}

    /**
     * Виведення звіту про баланс [cite: 221]
     */
    public static void printBalanceReport(double totalBalance) { // [cite: 213]
        System.out.println("Загальний баланс: " + totalBalance); // [cite: 214]
    }

    /**
     * Звіт по транзакціях за місяць [cite: 222]
     */
    public static void printTransactionsCountByMonth(String monthYear, int count) { // [cite: 216]
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count); // [cite: 217]
    }

    /**
     * Звіт про 10 найбільших витрат [cite: 253]
     */
    public static void printTopExpensesReport(List<Transaction> topExpenses) { // [cite: 257]
        System.out.println("10 найбільших витрат:"); // [cite: 258]
        for (Transaction expense : topExpenses) { // [cite: 259]
            System.out.println(expense.getDescription() + ": " + expense.getAmount()); // [cite: 260]
        }
    }
    // ... (всередині класу TransactionReportGenerator)

    /**
     * Самостійна робота: Звіт про найбільшу/найменшу витрату [cite: 283]
     */
    public static void printMinMaxExpenseReport(String monthYear, Transaction minExpense, Transaction maxExpense) {
        System.out.println("\n--- Звіт за " + monthYear + " ---");
        if (maxExpense != null) {
            System.out.println("Найбільша витрата: " + maxExpense.getDescription() + " (" + maxExpense.getAmount() + ")");
        } else {
            System.out.println("Найбільша витрата: немає даних");
        }

        if (minExpense != null) {
            System.out.println("Найменша витрата: " + minExpense.getDescription() + " (" + minExpense.getAmount() + ")");
        } else {
            System.out.println("Найменша витрата: немає даних");
        }
    }

    // [cite: 285] Кожен символ '*' представляє 1000 грн витрат
    private static final double VISUALIZATION_UNIT = 1000.0;

    /**
     * Самостійна робота: Текстовий звіт по категоріях [cite: 284]
     */
    public static void printCategoryExpenseReport(java.util.Map<String, Double> categorySummary) {
        System.out.println("\n--- Звіт витрат по категоріях ---");
        System.out.println("(Кожен '*' = " + VISUALIZATION_UNIT + " грн)");

        categorySummary.entrySet().stream()
                .sorted(java.util.Map.Entry.comparingByValue()) // Сортуємо (найбільші витрати вгорі)
                .forEach(entry -> {
                    String category = entry.getKey();
                    double amount = entry.getValue();
                    // [cite: 285]
                    String visualization = "*".repeat((int) (Math.abs(amount) / VISUALIZATION_UNIT));
                    System.out.printf("%-20s: %.2f %s\n", category, amount, visualization);
                });
    }

    /**
     * Самостійна робота: Текстовий звіт по місяцях [cite: 284]
     */
    public static void printMonthlyExpenseReport(java.util.Map<String, Double> monthlySummary) {
        System.out.println("\n--- Звіт витрат по місяцях ---");
        System.out.println("(Кожен '*' = " + VISUALIZATION_UNIT + " грн)");

        monthlySummary.entrySet().stream()
                .sorted(java.util.Map.Entry.comparingByKey()) // Сортуємо за датою
                .forEach(entry -> {
                    String month = entry.getKey();
                    double amount = entry.getValue();
                    // [cite: 285]
                    String visualization = "*".repeat((int) (Math.abs(amount) / VISUALIZATION_UNIT));
                    System.out.printf("%-10s: %.2f %s\n", month, amount, visualization);
                });
    }
}