/**
 * Ця реалізація ТОЧНО відповідає наданій діаграмі.
 * Вона некоректно обробляє рядки з перетинами, як-от "TETEST".
 */
public class FlawedAutomaton {

    private State currentState;

    /**
     * Обробити вхідний рядок, символ за символом.
     * @param input Вхідний рядок
     * @return Кінцевий стан автомата
     */
    public State process(String input) {
        currentState = State.S; // Завжди починаємо зі стану S
        for (char c : input.toCharArray()) {
            processChar(c);
        }
        return currentState;
    }

    private void processChar(char c) {
        switch (currentState) {
            case S:
                if (c == 'T') currentState = State._1;
                else currentState = State.S; // !T -> S
                break;

            case _1: // Маємо 'T'
                if (c == 'E') currentState = State._2;
                else currentState = State.S; // !E -> S
                break;

            case _2: // Маємо 'TE'
                if (c == 'S') currentState = State._3;
                else currentState = State.S; // !S -> S
                break;

            case _3: // Маємо 'TES'
                if (c == 'T') currentState = State.F;
                else currentState = State.S; // !T -> S
                break;

            case F:
                // * -> F (Залишаємося у фінальному стані)
                currentState = State.F;
                break;
        }
    }
}