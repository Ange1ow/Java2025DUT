package ua.dut.finance.analyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.dut.finance.Transaction;
import ua.dut.finance.TransactionCSVReader;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        // Створення тестових даних
        Transaction t1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction t2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction t3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result);
    }

    @Test
    public void testCountTransactionsByMonth() {
        // Підготовка тестових даних
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb);
        Assertions.assertEquals(1, countMar);
    }

    @Test
    public void testReadTransactionsFromCSV() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        // Перевіряємо, що список не порожній і щось прочитав
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");

        // Перевіряємо перший елемент (після пропуску заголовка)
        Transaction firstTransaction = transactions.get(0);
        Assertions.assertEquals("05-12-2023", firstTransaction.getDate());
        Assertions.assertEquals(-450.0, firstTransaction.getAmount());
        Assertions.assertEquals("Сільпо", firstTransaction.getDescription());
    }
    @Test
    public void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2023", -100.0, "A");
        Transaction t2 = new Transaction("02-01-2023", -500.0, "B");
        Transaction t3 = new Transaction("03-01-2023", 200.0, "C (Дохід)");
        Transaction t4 = new Transaction("04-01-2023", -1000.0, "D");
        Transaction t5 = new Transaction("05-01-2023", -50.0, "E");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3, t4, t5);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        // Повинно бути 4 витрати, але метод повертає до 10
        Assertions.assertEquals(4, topExpenses.size());
        // Перевіряємо, що D (-1000.0) є першою у списку (найбільша витрата)
        Assertions.assertEquals(t4, topExpenses.get(0));
        // Перевіряємо, що B (-500.0) є другою
        Assertions.assertEquals(t2, topExpenses.get(1));
        // Перевіряємо, що E (-50.0) є останньою (найменша витрата)
        Assertions.assertEquals(t5, topExpenses.get(3));
    }
}