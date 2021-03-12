package junit5.extension.testwatcher.success;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SuccessTestWatcher.class)
class SuccessTestWatcherTest {

    @BeforeAll
    public static void reset() {
        SuccessTestWatcher.clearResults();
    }

    @Order(0)
    @Test
    void firstTest() {
        assertThat(true).isTrue();
    }

    @Order(1)
    @Test
    void secondTest() {
        assertThat(SuccessTestWatcher.getResults()).hasSize(1);
        assertThat(SuccessTestWatcher.getResults().get(0)).isEqualTo("Assert that true is true");
    }
}