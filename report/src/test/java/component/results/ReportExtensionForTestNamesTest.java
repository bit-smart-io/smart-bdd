package component.results;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shared.undertest.TestNamesTest;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportExtensionForTestNamesTest {
    private static TestSuiteResult testSuiteResult;

    @BeforeAll
    static void beforeAll() {
        ReportExtension.getTestContext().reset();
        TestLauncher.launch(TestNamesTest.class);
        testSuiteResult = testSuiteResult(TestNamesTest.class);
    }

    @Test
    void verifyFirstTest_noParams() {
        TestCaseResult testCaseResult = testCase(0);
        assertThat(testCaseResult.getName()).isEqualTo("testMethod");
        assertThat(testCaseResult.getMethodName()).isEqualTo("testMethod");
    }

    @Test
    void verifySecondTest_paramsNoCustomName() {
        assertThat(testCase(1).getName()).isEqualTo("paramTest value 1");
        assertThat(testCase(1).getMethodName()).isEqualTo("paramTest");

        assertThat(testCase(2).getName()).isEqualTo("paramTest value 2");
        assertThat(testCase(2).getMethodName()).isEqualTo("paramTest");

        assertThat(testCase(3).getName()).isEqualTo("paramTest value 3");
        assertThat(testCase(3).getMethodName()).isEqualTo("paramTest");
    }

    @Disabled
    @Test
    void verifyThirdTest_paramsWithCustomName() {
        assertThat(testCase(4).getName()).isEqualTo("#0 - value = 1");
        assertThat(testCase(4).getMethodName()).isEqualTo("paramTestWithCustomName");

        assertThat(testCase(5).getName()).isEqualTo("#0 - value = 2");
        assertThat(testCase(5).getMethodName()).isEqualTo("paramTestWithCustomName");

        assertThat(testCase(6).getName()).isEqualTo("#0 - value = 3");
        assertThat(testCase(6).getMethodName()).isEqualTo("paramTestWithCustomName");
    }

    private TestCaseResult testCase(int index) {
        return testSuiteResult.getTestCaseResults().get(index);
    }

    private static TestSuiteResult testSuiteResult(Class<?> clazz) {
        return ReportExtension.getTestContext().getTestResults().getTestSuiteResults(testSuiteClass(clazz));
    }
}
