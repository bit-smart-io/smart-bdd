package junit5.results.undertest;

import junit5.results.ResultsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ResultsExtension.class)
public class FailedTestCasesUnderTest {

    @Test
    void testMethod() {
        assertThat("testMethod").isNotEqualTo("testMethod");
    }

    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String key) {
        assertThat(key).isNull();
    }
}