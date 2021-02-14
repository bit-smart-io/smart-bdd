package junit5.extension.testwatcher;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MyTestWatcher.class)
class MyTestWatcherTest {

    @BeforeAll
    public static void reset() {
        MyTestWatcher.clearResults();
    }

    @Order(0)
    @Test
    void firstTest() {
        assertThat(true).isTrue();
    }

    @Order(1)
    @Test
    void secondTest() {
        assertThat(MyTestWatcher.getResult()).hasSize(1);
        assertThat(MyTestWatcher.getResult().get(0)).isEqualTo("assert that true is true");
    }
}