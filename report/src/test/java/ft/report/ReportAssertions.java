package ft.report;

import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestCase;
import io.bitsmart.bdd.report.report.model.TestSuite;

import static ft.report.builders.TestCaseBuilder.aTestCase;
import static ft.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportAssertions {

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
        assertThat(testSuite.getTestCases()).contains(passingParamTestCase());
    }

    public static TestCase passingTestCase() {
        return aTestCase()
            .withWordify("Passing assertion")
            .withStatus(Status.PASSED)
            .withName("testMethod")
            .withMethodName("testMethod")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .build();
    }

    public static TestCase passingParamTestCase() {
        return aTestCase()
            .withWordify("Passing assertion with value 1")
            .withStatus(Status.PASSED)
            .withName("paramTest value 1")
            .withMethodName("paramTest")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .build();
    }
}
