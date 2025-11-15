import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlawedAutomatonTest {

    private FlawedAutomaton automaton;

    @BeforeEach
    void setUp() {
        automaton = new FlawedAutomaton();
    }

    @ParameterizedTest
    @CsvSource({
            // --- Базові випадки з завдання ---
            "F,  abcTESTabc",   // Приклад 1: Слово "TEST" знайдено
            "_3, abcTES",       // Приклад 2: Зупинка на "TES"

            // --- Основні переходи ---
            "F,  TEST",         // Пряме знаходження слова
            "_3, TES",          // Зупинка на стані _3
            "F,  TESTT",        // Перевірка переходу * зі стану F (має залишитись в F)
            "_1, T",            // Перевірка базового переходу S -> _1

            // --- Тести на НЕКОРЕКТНУ логіку діаграми (Завдання 3*) ---
            // Ці тести доводять, що автомат працює НЕПРАВИЛЬНО,
            // але ВІДПОВІДНО до діаграми.

            // Вхід: "TTTTEST"
            // Логіка діаграми: S -> T -> _1 -> T(!E) -> S ...
            // В результаті автомат бачить "TT" і скидається в S.
            // Потім знову бачить "TT" і скидається.
            // Лише останній "T" переводить його в _1.
            "_1, TTTTEST",

            // Вхід: "TETEST"
            // Логіка діаграми: S -> T -> _1 -> E -> _2 -> T(!S) -> S
            // Автомат бачить "TET" і скидається в S.
            // Далі він обробляє "EST", але "TEST" вже пропущено.
            // На останньому "T" він переходить в _1.
            "_1, TETEST"
    })
    void testFlawedAutomatonLogic(State expectedState, String input) {
        assertEquals(expectedState, automaton.process(input),
                "Вхід: \"" + input + "\"");
    }
}