package shared.undertest;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(ReportExtension.class)
public class TestNamesTest {

    @Order(0)
    @Test
    void testMethod() {
        passingAssertion();
    }

    @ParameterizedTest
    @Order(1)
    @ValueSource(strings = {"value 1", "value 2", "value 3"})
    void paramTest(String param) {
        passingAssertionWith(param);
    }

    @ParameterizedTest(name = "{index} - value = {0}")
    @Order(2)
    @ValueSource(strings = {"value 1", "value 2", "value 3"})
    void paramTestWithCustomName(String param) {
        passingAssertionWith(param);
    }

    private void passingAssertion() {
        assertThat(true).isTrue();
    }

    private void passingAssertionWith(String param) {
        assertThat(param).isNotNull();
    }
}