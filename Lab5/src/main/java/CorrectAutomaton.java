/**
 * Ця реалізація КОРЕКТНО розпізнає "TEST",
 * враховуючи всі можливі перетини послідовностей.
 */
public class CorrectAutomaton {

    private State currentState;

    public State process(String input) {
        currentState = State.S;
        for (char c : input.toCharArray()) {
            processChar(c);
        }
        return currentState;
    }

    /**
     * Коректна логіка переходів (KMP-подібний автомат).
     */
    private void processChar(char c) {
        switch (currentState) {
            case S:
                if (c == 'T') currentState = State._1;
                // else залишаємося в S
                break;

            case _1: // Маємо "T"
                if (c == 'E') currentState = State._2;
                else if (c != 'T') currentState = State.S;
                // else (якщо c == 'T') залишаємося в _1 (випадок "TT...")
                break;

            case _2: // Маємо "TE"
                if (c == 'S') currentState = State._3;
                else if (c == 'T') currentState = State._1; // "TET..." -> збіг "T"
                else currentState = State.S; // "TEX..." -> скидання
                break;

            case _3: // Маємо "TES"
                if (c == 'T') currentState = State.F; // "TEST" -> Успіх!
                else if (c == 'T') currentState = State._1; // "TEST..." -> Початок нового T (вже оброблено)
                else currentState = State.S; // "TESX..." -> скидання
                break;

            case F:
                // * -> F (Залишаємося у фінальному стані)
                currentState = State.F;
                break;
        }
    }
}