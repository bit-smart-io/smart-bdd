package ft.report;

import io.bitsmart.bdd.report.junit5.results.ReportFactory;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuite;
import shared.undertest.ClassUnderTest;

import java.io.IOException;

import static ft.report.ReportAssertions.assertPassingTestSuite;
import static ft.report.ReportTestUtils.loadTestSuite;
import static ft.report.ReportTestUtils.writeReport;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.learning.parameters.LearningTest" tests="8" skipped="0" failures="0" errors="0" timestamp="2021-03-30T20:03:44" hostname="Jamess-MacBook-Pro.local" time="0.021">
 *   <properties/>
 *   <testcase name="[1] test, TEST" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="[2] tEst, TEST" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="[3] Java, JAVA" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="RepeatingTest 1/5" classname="junit5.learning.parameters.LearningTest" time="0.005"/>
 *   <testcase name="RepeatingTest 2/5" classname="junit5.learning.parameters.LearningTest" time="0.0"/>
 *   <testcase name="RepeatingTest 3/5" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="RepeatingTest 4/5" classname="junit5.learning.parameters.LearningTest" time="0.0"/>
 *   <testcase name="RepeatingTest 5/5" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <system-out><![CDATA[RepeatingTest 1/5-->1
 * RepeatingTest 2/5-->2
 * RepeatingTest 3/5-->3
 * RepeatingTest 4/5-->4
 * RepeatingTest 5/5-->5
 * ]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
public class ReportForPassingTestSuiteTest {
    private static final Class<?> PASSING_CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ReportExtension.reset();
    }

    @Test
    void reportForOneClassGeneratedCorrectly() throws IOException {
        TestLauncher.launch(PASSING_CLASS_UNDER_TEST);
        Report report = ReportFactory.create(ReportExtension.getResults());
        writeReport(report);

        assertReport(report);
        assertPassingTestSuite(loadTestSuite(PASSING_CLASS_UNDER_TEST));
    }

    public static void assertReport(Report report) {
        assertThat(report).isNotNull();
        assertThat(report.getTestCases()).hasSize(4);
        assertThat(report.getTestSuites()).hasSize(1);

        TestSuite testSuite = report.getTestSuites().get(0);
        assertPassingTestSuite(testSuite);
    }
}
