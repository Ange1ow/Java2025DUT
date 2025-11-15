package ua.dut.finance;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        // --- Основні завдання ---
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // --- Самостійна робота [cite: 286] ---

        // 1. Звіт про мінімальні та максимальні витрати [cite: 283]
        String reportMonth = "12-2023"; // Візьмемо інший місяць для прикладу
        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseByMonth(transactions, reportMonth);
        Transaction minExpense = TransactionAnalyzer.findMinExpenseByMonth(transactions, reportMonth);
        TransactionReportGenerator.printMinMaxExpenseReport(reportMonth, minExpense, maxExpense);

        // 2. Звіт по категоріях з візуалізацією [cite: 284]
        Map<String, Double> categorySummary = TransactionAnalyzer.summarizeExpensesByCategory(transactions);
        TransactionReportGenerator.printCategoryExpenseReport(categorySummary);

        // 3. Звіт по місяцях з візуалізацією [cite: 284]
        Map<String, Double> monthlySummary = TransactionAnalyzer.summarizeExpensesByMonth(transactions);
        TransactionReportGenerator.printMonthlyExpenseReport(monthlySummary);
    }
}