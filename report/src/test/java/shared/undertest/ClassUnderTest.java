package shared.undertest;

import junit5.results.ResultsExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.results.undertest.ClassUnderTest" tests="4" skipped="0" failures="0" errors="0" timestamp="2021-04-12T20:09:43" hostname="Jamess-MacBook-Pro.local" time="0.011">
 *   <properties/>
 *   <testcase name="testMethod()" classname="junit5.results.undertest.ClassUnderTest" time="0.002"/>
 *   <testcase name="[1] value 1" classname="junit5.results.undertest.ClassUnderTest" time="0.002"/>
 *   <testcase name="[2] value 2" classname="junit5.results.undertest.ClassUnderTest" time="0.002"/>
 *   <testcase name="[3] value 3" classname="junit5.results.undertest.ClassUnderTest" time="0.002"/>
 *   <system-out><![CDATA[]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(ResultsExtension.class)
public class ClassUnderTest {

    @Order(0)
    @Test
    void testMethod() {
        passingAssertion();
    }

    @ParameterizedTest
    @Order(1)
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String param) {
        passingAssertionWith(param);
    }

    private void passingAssertion() {
        assertThat(true).isTrue();
    }

    private void passingAssertionWith(String param) {
        assertThat(param).isNotNull();
    }
}