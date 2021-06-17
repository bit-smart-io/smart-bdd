package shared.undertest;

import junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.results.undertest.FailedTestCasesUnderTest" tests="4" skipped="0" failures="4" errors="0" timestamp="2021-04-12T20:09:43" hostname="Jamess-MacBook-Pro.local" time="0.189">
 *   <properties/>
 *   <testcase name="testMethod()" classname="junit5.results.undertest.FailedTestCasesUnderTest" time="0.12">
 *     <failure message="java.lang.AssertionError: &#10;Expecting:&#10; &lt;&quot;testMethod&quot;&gt;&#10;not to be equal to:&#10; &lt;&quot;testMethod&quot;&gt;&#10;" type="java.lang.AssertionError">java.lang.AssertionError:
 * Expecting:
 *  &lt;&quot;testMethod&quot;&gt;
 * not to be equal to:
 *  &lt;&quot;testMethod&quot;&gt;
 *
 * 	at junit5.results.undertest.FailedTestCasesUnderTest.testMethod(FailedTestCasesUnderTest.java:16) ...
 *   </testcase>
 *   <system-out><![CDATA[]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
@ExtendWith(ReportExtension.class)
public class FailedTestCasesUnderTest {

    @Test
    void testMethod() {
        failingAssertion();
    }

    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String param) {
        failingAssertionWith(param);
    }

    private void failingAssertion() {
        assertThat(true).isFalse();
    }

    private void failingAssertionWith(String param) {
        assertThat(param).isNull();
    }
}