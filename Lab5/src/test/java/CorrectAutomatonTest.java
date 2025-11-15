import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CorrectAutomatonTest {

    private CorrectAutomaton automaton;

    @BeforeEach
    void setUp() {
        automaton = new CorrectAutomaton();
    }

    @ParameterizedTest
    @CsvSource({
            "F, abcTESTabc",
            "_3, abcTES",
            "F, TEST",
            "_3, TES",
            "F, TESTT",
            "F, TTTTEST",
            "F, TETEST",
            "F, TESTTEST",
            "S, TESE",
            "_1, TET"
    })
    void testCorrectAutomatonLogic(State expectedState, String input) {
        assertEquals(expectedState, automaton.process(input),
                "Вхід: \"" + input + "\"");
    }
}