package shared.undertest;

import junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.results.undertest.DisabledTestCasesUnderTest" tests="1" skipped="1" failures="0" errors="0" timestamp="2021-04-12T20:09:43" hostname="Jamess-MacBook-Pro.local" time="0.0">
 *   <properties/>
 *   <testcase name="testMethod()" classname="junit5.results.undertest.DisabledTestCasesUnderTest" time="0.0">
 *     <skipped/>
 *   </testcase>
 *   <system-out><![CDATA[]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
@ExtendWith(ReportExtension.class)
public class DisabledTestCasesUnderTest {

    @Disabled
    @Test
    void testMethod() {
        disabledAssertion();
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = { "value 1", "value 2", "value 3" })
    void paramTest(String param) {
        disabledAssertionWith(param);
    }

    private void disabledAssertion() {
        assertThat(true).isTrue();
    }

    private void disabledAssertionWith(String param) {
        assertThat(param).isNotNull();
    }
}