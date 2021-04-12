package junit5.results.undertest;

import junit5.results.ResultsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(ResultsExtension.class)
public class ExceptionThrownTestCasesUnderTest {

    @Test
    void testMethod() {
        methodThatThrowsAPointerMethod();
    }

    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String key) {
        methodThatThrowsAPointerMethod();
    }

    private void methodThatThrowsAPointerMethod() {
        String str = null;
        str.toLowerCase();
    }
}