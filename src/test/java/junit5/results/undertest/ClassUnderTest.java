package junit5.results.undertest;

import junit5.results.ResultsExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(ResultsExtension.class)
public class ClassUnderTest {

    @Order(0)
    @Test
    void testMethod() {
        assertThat("testMethod").isEqualTo("testMethod");
    }

    @ParameterizedTest
    @Order(1)
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String key) {
        assertThat(key).isNotNull();
    }
}