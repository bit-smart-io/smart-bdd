package junit5.extension.testwatcher;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(TestResultsWatcher.class)
class TestResultsWatcherComponentTest {

    @BeforeAll
    public static void reset() {
        TestResultsWatcher.clearResults();
    }

    @ParameterizedTest
    @Order(1)
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void firstTest(String key) {
        assertThat(key).isNotNull();
    }

    @Test
    @Order(2)
    void generatesTestResults() {
        assertThat(TestResultsWatcher.getParams()).hasSize(3);
        assertThat(TestResultsWatcher.getParams().get(0)).containsExactly("value 1");
        assertThat(TestResultsWatcher.getParams().get(1)).containsExactly("value 2");
        assertThat(TestResultsWatcher.getParams().get(2)).containsExactly("value 3");
        assertThat(TestResultsWatcher.getResult().get(0)).isEqualTo("assert that value 1 is not null");
        assertThat(TestResultsWatcher.getResult().get(1)).isEqualTo("assert that value 2 is not null");
        assertThat(TestResultsWatcher.getResult().get(2)).isEqualTo("assert that value 3 is not null");
    }
}