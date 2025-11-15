package ua.dut.finance;

import java.util.List;

public abstract class TransactionReportGenerator {

    //Приватний конструктор
    private TransactionReportGenerator() {}

    //Виведення звіту про баланс
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    //Звіт по транзакціях за місяць
    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    //Звіт про 10 найбільших витрат
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
    //Звіт про найбільшу/найменшу витрату
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

    //Кожен символ '*' представляє 1000 грн витрат
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
                    String visualization = "*".repeat((int) (Math.abs(amount) / VISUALIZATION_UNIT));
                    System.out.printf("%-20s: %.2f %s\n", category, amount, visualization);
                });
    }

    //Текстовий звіт по місяцях
    public static void printMonthlyExpenseReport(java.util.Map<String, Double> monthlySummary) {
        System.out.println("\n--- Звіт витрат по місяцях ---");
        System.out.println("(Кожен '*' = " + VISUALIZATION_UNIT + " грн)");

        monthlySummary.entrySet().stream()
                .sorted(java.util.Map.Entry.comparingByKey()) // Сортуємо за датою
                .forEach(entry -> {
                    String month = entry.getKey();
                    double amount = entry.getValue();
                    String visualization = "*".repeat((int) (Math.abs(amount) / VISUALIZATION_UNIT));
                    System.out.printf("%-10s: %.2f %s\n", month, amount, visualization);
                });
    }
}