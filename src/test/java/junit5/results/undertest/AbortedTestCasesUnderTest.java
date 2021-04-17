package junit5.results.undertest;

import junit5.results.ResultsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Not sure why the generated report states skipped. But the TestWatcher extension raises an aborted event.
 *
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.results.undertest.AbortedTestCasesUnderTest" tests="4" skipped="4" failures="0" errors="0" timestamp="2021-04-12T20:09:43" hostname="Jamess-MacBook-Pro.local" time="0.011">
 *   <properties/>
 *   <testcase name="testMethod()" classname="junit5.results.undertest.AbortedTestCasesUnderTest" time="0.002">
 *     <skipped/>
 *   </testcase>
 *   <testcase name="[1] value 1" classname="junit5.results.undertest.AbortedTestCasesUnderTest" time="0.002">
 *     <skipped/>
 *   </testcase>
 *   <testcase name="[2] value 2" classname="junit5.results.undertest.AbortedTestCasesUnderTest" time="0.002">
 *     <skipped/>
 *   </testcase>
 *   <testcase name="[3] value 3" classname="junit5.results.undertest.AbortedTestCasesUnderTest" time="0.002">
 *     <skipped/>
 *   </testcase>
 *   <system-out><![CDATA[]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
@ExtendWith(ResultsExtension.class)
public class AbortedTestCasesUnderTest {

    @Test
    void testMethod() {
        assumeTrue("testMethod".contains("Z"), "testMethod does not contain Z");
    }

    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String key) {
        assumeTrue(key.contains("Z"), "abc does not contain Z");
    }
}