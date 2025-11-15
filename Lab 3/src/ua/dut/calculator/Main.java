package ua.dut.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Створюємо Scanner в try-with-resources, щоб він автоматично закрився.
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть операцію (+, -, *, /, r [для кореня]): ");
            char operation = scanner.next().charAt(0);

            double num1, num2, result;

            if (operation == 'r') {
                //Обробка операцій з одним числом
                System.out.print("Введіть число: ");
                num1 = scanner.nextDouble();
                result = calculator.sqrt(num1); // Може кинути InvalidInputException
                System.out.println("Результат: " + result);

            } else if (operation == '+' || operation == '-' || operation == '*' || operation == '/') {
                //Обробка операцій з двома числами
                System.out.print("Введіть перше число: ");
                num1 = scanner.nextDouble(); //Може кинути InputMismatchException
                System.out.print("Введіть друге число: ");
                num2 = scanner.nextDouble(); //Може кинути InputMismatchException

                switch (operation) {
                    case '+':
                        result = calculator.add(num1, num2);
                        break;
                    case '-':
                        result = calculator.subtract(num1, num2);
                        break;
                    case '*':
                        result = calculator.multiply(num1, num2);
                        break;
                    case '/':
                        result = calculator.divide(num1, num2); //Може кинути ArithmeticException
                        break;
                    default:
                        //Це виключення не повинно спрацювати, але це гарна практика
                        throw new Exception("Невідома логічна помилка.");
                }
                System.out.println("Результат: " + result);
            } else {
                System.out.println("Помилка: Невідома операція.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Помилка вводу: Ви ввели не число.");

        } catch (ArithmeticException | InvalidInputException e) {
            //ділення на нуль/корінь з від'ємного числа
            //ми обробляємо обидва наших "очікуваних" виключення тут
            System.out.println("Помилка обчислення: " + e.getMessage());

        } catch (Exception e) {
            //Для всіх інших непередбачуваних помилок
            System.out.println("Виникла непередбачувана помилка: " + e.getMessage());

        } finally {
            System.out.println("\nЗавершення обробки запиту");
            scanner.close();
        }
    }
}