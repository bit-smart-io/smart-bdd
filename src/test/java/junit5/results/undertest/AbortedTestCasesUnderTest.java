package junit5.results.undertest;

import junit5.results.ResultsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(ResultsExtension.class)
public class AbortedTestCasesUnderTest {

    @Test
    void testMethod() {
        assumeTrue("testMethod".contains("Z"), "testMethod does not contain Z");
    }

    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String key) {
        assumeTrue(key.contains("Z"), "abc does not contain Z");
    }
}