package ua.dut.calculator;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            //Кидаємо вбудоване виключення
            throw new ArithmeticException("Ділення на нуль заборонено!");
        }
        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        if (a < 0) {
            // Кидаємо наше власне виключення
            throw new InvalidInputException("Неможливо взяти квадратний корінь з від'ємного числа.");
        }
        return Math.sqrt(a);
    }
}