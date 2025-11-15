package ua.dut.calculator;

/**
 * Власне виключення, яке ми кидаємо,
 * коли вхідні дані для операції є некоректними
 * (наприклад, від'ємне число для квадратного кореня).
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}