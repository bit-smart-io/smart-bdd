package shared.undertest;

import junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.results.undertest.ExceptionThrownTestCasesUnderTest" tests="4" skipped="0" failures="4" errors="0" timestamp="2021-04-12T20:09:43" hostname="Jamess-MacBook-Pro.local" time="0.024">
 *   <properties/>
 *   <testcase name="testMethod()" classname="junit5.results.undertest.ExceptionThrownTestCasesUnderTest" time="0.006">
 *     <failure message="java.lang.NullPointerException" type="java.lang.NullPointerException">java.lang.NullPointerException ...
 *   </testcase>
 *   <testcase name="[1] value 1" classname="junit5.results.undertest.ExceptionThrownTestCasesUnderTest" time="0.005">
 *     <failure message="java.lang.NullPointerException" type="java.lang.NullPointerException">java.lang.NullPointerException ...
 *   </testcase>
 *   <testcase name="[2] value 2" classname="junit5.results.undertest.ExceptionThrownTestCasesUnderTest" time="0.003">
 *     <failure message="java.lang.NullPointerException" type="java.lang.NullPointerException">java.lang.NullPointerException ...
 *   </testcase>
 *   <testcase name="[3] value 3" classname="junit5.results.undertest.ExceptionThrownTestCasesUnderTest" time="0.002">
 *     <failure message="java.lang.NullPointerException" type="java.lang.NullPointerException">java.lang.NullPointerException ....
 *   </testcase>
 *   <system-out><![CDATA[]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
@ExtendWith(ReportExtension.class)
public class FailedDueToExceptionTestCasesUnderTest {

    @Test
    void testMethod() {
        methodThatThrowsAPointerMethod();
    }

    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String param) {
        methodThatThrowsAPointerMethodWith(param);
    }

    private void methodThatThrowsAPointerMethod() {
        String str = null;
        str.toLowerCase();
    }

    private void methodThatThrowsAPointerMethodWith(String param) {
        String str = null;
        str.toLowerCase();
    }
}