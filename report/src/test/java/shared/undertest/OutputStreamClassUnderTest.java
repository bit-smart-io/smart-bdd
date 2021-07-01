package shared.undertest;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(ReportExtension.class)
public class OutputStreamClassUnderTest {

    @Order(0)
    @Test
    void testMethod() {
        System.out.println("system.out for testMethod");
        System.err.println("system.err for testMethod");
        passingAssertion();
    }

    private void passingAssertion() {
        assertThat(true).isTrue();
    }
}