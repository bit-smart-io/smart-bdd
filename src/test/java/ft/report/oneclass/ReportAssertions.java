package ft.report.oneclass;

import report.model.Report;
import report.model.Status;
import report.model.TestCase;
import report.model.TestSuite;

import static ft.report.builders.TestCaseBuilder.aTestCase;
import static ft.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportAssertions {

    public static void assertPassingReport(Report report) {
        assertThat(report).isNotNull();
        assertThat(report.getTestCases()).hasSize(4);

        assertThat(report.getTestSuites()).hasSize(1);
        TestSuite testSuite = report.getTestSuites().get(0);
        assertPassingTestSuite(testSuite);
    }

    public static void assertPassingTestSuite(TestSuite testSuite) {
        assertThat(testSuite.getName()).isEqualTo("shared.undertest.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("shared.undertest");
        assertThat(testSuite.getMethodNames()).containsExactly("testMethod", "paramTest", "paramTest", "paramTest");
        assertThat(testSuite.getSummary()).isEqualTo(
            aTestSuiteSummary()
                .withTestCaseCount(4)
                .withPassedCount(4)
                .build());
        assertThat(testSuite.getTestCases()).contains(passingTestCase());
    }

    public static TestCase passingTestCase() {
        return aTestCase()
            .withWordify("Passing assertion")
            .withStatus(Status.PASSED)
            .withMethodName("testMethod")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .build();
    }
}
