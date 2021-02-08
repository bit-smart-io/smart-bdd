package junit5.extension.testwatcher;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MyTestWatcher.class)
class MyTestWatcherTest {

    @Order(0)
    @Test
    void firstTest() {
        assertThat(true).isTrue();
    }

    @Order(1)
    @Test
    void secondTest() {
        assertThat(MyTestWatcher.getResult()).isEqualTo("assert that true is true");
    }
}