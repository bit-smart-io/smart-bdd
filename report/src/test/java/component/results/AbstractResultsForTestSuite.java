package component.results;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestMethod;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractResultsForTestSuite {
    private TestSuiteResult testSuiteResult;

    @BeforeEach
    void beforeAll() {
        ReportExtension.getTestContext().reset();
        TestLauncher.launch(classUnderTest());
        testSuiteResult = testSuiteResult(classUnderTest());
    }

    public abstract Class<?> classUnderTest();

    public TestSuiteClass testSuiteClass() {
        return TestSuiteClass.testSuiteClass(classUnderTest());
    }

    public TestCaseResult testCase(int index) {
        return testSuiteResult.getTestCaseResults().get(index);
    }

    public TestSuiteResult testSuiteResult() {
        return testSuiteResult;
    }

    private TestSuiteResult testSuiteResult(Class<?> clazz) {
        return ReportExtension.getTestContext().getTestResults().getTestSuiteResults(TestSuiteClass.testSuiteClass(clazz));
    }

    protected TestMethod method(String name) {
        return new TestMethod(name);
    }

    protected void assertTestSuitClass(TestSuiteResult testSuiteResult, Class<?> clazz) {
        assertThat(testSuiteResult.getTestSuiteClass().getClassName()).isEqualTo(clazz.getSimpleName());
        assertThat(testSuiteResult.getTestSuiteClass().getFullyQualifiedName()).isEqualTo(clazz.getPackage().getName() + "." + clazz.getSimpleName());
        assertThat(testSuiteResult.getTestSuiteClass().getPackageName()).isEqualTo(clazz.getPackage().getName());
    }

    protected void assertCauseWithMessage(TestCaseResult result, String message) {
        Throwable cause = result.getCause().orElseThrow(() -> new RuntimeException("Expected cause"));
        assertThat(cause.getMessage()).isEqualTo(message);
        assertThat(cause.getClass()).isNotNull();
        assertThat(cause.getCause()).isNull();
        assertThat(cause.getStackTrace()).isNotNull();
    }
}
