package ua.dut.finance.analyzer;

import org.junit.jupiter.api.Assertions; // [cite: 144]
import org.junit.jupiter.api.Test; // [cite: 145]
import ua.dut.finance.Transaction;
import ua.dut.finance.TransactionCSVReader;

import java.util.Arrays; // [cite: 146]
import java.util.List; // [cite: 147]

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() { // [cite: 125]
        // Створення тестових даних [cite: 126]
        Transaction t1 = new Transaction("2023-01-01", 100.0, "Дохід"); // [cite: 127]
        Transaction t2 = new Transaction("2023-01-02", -50.0, "Витрата"); // [cite: 128]
        Transaction t3 = new Transaction("2023-01-03", 150.0, "Дохід"); // [cite: 129]
        List<Transaction> transactions = Arrays.asList(t1, t2, t3); // [cite: 130]

        double result = TransactionAnalyzer.calculateTotalBalance(transactions); // [cite: 134]
        Assertions.assertEquals(200.0, result); // [cite: 136]
    }

    @Test
    public void testCountTransactionsByMonth() { // [cite: 192]
        // Підготовка тестових даних [cite: 193]
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Дохід"); // [cite: 194]
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата"); // [cite: 195]
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Дохід"); // [cite: 196]
        List<Transaction> transactions = Arrays.asList(t1, t2, t3); // [cite: 197]

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023"); // [cite: 200]
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023"); // [cite: 201]

        Assertions.assertEquals(2, countFeb); // [cite: 203]
        Assertions.assertEquals(1, countMar); // [cite: 204]
    }

    /**
     * Новий тест для самостійної роботи [cite: 280]
     * Це більше інтеграційний тест, але він перевіряє читання
     */
    @Test
    public void testReadTransactionsFromCSV() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv"; // [cite: 6]
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        // Перевіряємо, що список не порожній і щось прочитав
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");

        // Перевіряємо перший елемент (після пропуску заголовка)
        Transaction firstTransaction = transactions.get(0);
        Assertions.assertEquals("05-12-2023", firstTransaction.getDate());
        Assertions.assertEquals(-450.0, firstTransaction.getAmount());
        Assertions.assertEquals("Сільпо", firstTransaction.getDescription());
    }

    /**
     * Новий тест для самостійної роботи [cite: 281]
     */
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