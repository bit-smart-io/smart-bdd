package component.results;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import org.junit.jupiter.api.BeforeEach;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;

public abstract class AbstractResultsForClass {
    private TestSuiteResult testSuiteResult;

    @BeforeEach
    void beforeAll() {
        ReportExtension.getTestContext().reset();
        TestLauncher.launch(classUnderTest());
        testSuiteResult = testSuiteResult(classUnderTest());
    }

    public abstract Class<?> classUnderTest();

    public TestCaseResult testCase(int index) {
        return testSuiteResult.getTestCaseResults().get(index);
    }

    public TestSuiteResult testSuiteResult() {
        return testSuiteResult;
    }

    private TestSuiteResult testSuiteResult(Class<?> clazz) {
        return ReportExtension.getTestContext().getTestResults().getTestSuiteResults(testSuiteClass(clazz));
    }
}
