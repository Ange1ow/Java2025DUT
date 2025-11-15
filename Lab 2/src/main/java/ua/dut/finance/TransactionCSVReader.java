package ua.dut.finance;

import java.io.BufferedReader; // [cite: 64]
import java.io.IOException; // [cite: 65]
import java.io.InputStreamReader; // [cite: 66]
import java.net.URL; // [cite: 67]
import java.util.ArrayList; // [cite: 68]
import java.util.List; // [cite: 69]

// Вимога 4: Абстрактний клас
public abstract class TransactionCSVReader {

    // Вимога 4: Приватний конструктор, щоб заборонити створення екземплярів
    private TransactionCSVReader() {}

    // Вимога 4: Статичний метод
    public static List<Transaction> readTransactions(String filePath) { // [cite: 43]
        List<Transaction> transactions = new ArrayList<>(); // [cite: 44]
        try {
            URL url = new URL(filePath); // [cite: 46]
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) { // [cite: 48]

                // Пропускаємо перший рядок (заголовок "Дата,Сума,Категорія")
                br.readLine();

                String line;
                while ((line = br.readLine()) != null) { // [cite: 50]
                    String[] values = line.split(","); // [cite: 51]
                    if (values.length == 3) {
                        Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]); // [cite: 52]
                        transactions.add(transaction); // [cite: 53]
                    }
                }
            }
        } catch (IOException e) { // [cite: 56]
            e.printStackTrace(); // [cite: 57]
        }
        return transactions; // [cite: 59]
    }
}