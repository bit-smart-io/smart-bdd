package junit5.extension.testwatcher;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MyTestWatcher.class)
class MyTestWatcherWithParamsTest {

    @BeforeAll
    public static void reset() {
        MyTestWatcher.clearResults();
    }

    @Order(0)
    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void firstTest(String key) {
        assertThat(key).isNotNull();
    }

    @Disabled
    @Order(1)
    @Test
    void secondTest() {
        assertThat(MyTestWatcher.getResult()).hasSize(3);
        assertThat(MyTestWatcher.getResult().get(0)).isEqualTo("assert that value 1 is not null");
    }
}