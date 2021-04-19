package ft.report.oneclass;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import junit5.results.ResultsExtension;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(ResultsExtension.class)
class ClassUnderTest {

    @Order(0)
    @Test
    void firstTest() {
        assertThat("firstTest").isEqualTo("firstTest");
    }

    @Order(1)
    @Test
    void secondTest() {
        assertThat("secondTest").isEqualTo("secondTest");
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void thirdParamTest(String param) {
        assertThat(param).isNotNull();
    }
}