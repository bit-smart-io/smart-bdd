package junit5.extension.testwatcher;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import wordify.WordifyClass;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(TestResultsWatcher.class)
class TestResultsWatcherTest {

    // TODO will need a stub
    static WordifyClass wordify = mock(WordifyClass .class);

    @BeforeAll
    public static void reset() {
        TestResultsWatcher.setWordify(wordify);
        TestResultsWatcher.clearResults();
    }

    @AfterAll
    public static void afterAll() {
        TestResultsWatcher.setWordify(new WordifyClass());
    }

    @Order(0)
    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void firstTest(String key) {
        assertThat(key).isNotNull();
    }

    @Order(1)
    @Test
    void generatesTestResults() {
        verify(wordify).wordify(TestResultsWatcherTest.class, "firstTest", Arrays.asList("value 1"));
    }
}