package junit5.extension.testwatcher;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MyTestWatcherWithInterceptor.class)
class MyTestWatcherWithParametersTest {

    @BeforeAll
    public static void reset() {
        MyTestWatcherWithInterceptor.clearResults();
    }

    @Order(0)
    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void firstTest(String key) {
        assertThat(key).isNotNull();
    }

    @Order(1)
    @Test
    void secondTest() {
        assertThat(MyTestWatcherWithInterceptor.getParams()).hasSize(3);
        assertThat(MyTestWatcherWithInterceptor.getParams().get(0)).containsExactly("value 1");
        assertThat(MyTestWatcherWithInterceptor.getParams().get(1)).containsExactly("value 2");
        assertThat(MyTestWatcherWithInterceptor.getParams().get(2)).containsExactly("value 3");
        assertThat(MyTestWatcherWithInterceptor.getResult().get(0)).isEqualTo("assert that value 1 is not null");
        assertThat(MyTestWatcherWithInterceptor.getResult().get(1)).isEqualTo("assert that value 2 is not null");
        assertThat(MyTestWatcherWithInterceptor.getResult().get(2)).isEqualTo("assert that value 3 is not null");
    }
}