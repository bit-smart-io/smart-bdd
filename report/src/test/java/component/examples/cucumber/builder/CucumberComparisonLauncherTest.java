package component.examples.cucumber.builder;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;

import static io.bitsmart.bdd.report.junit5.results.model.ClassSimpleName.classSimpleName;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;
import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    private static TestSuiteResult testSuiteResult;

    @BeforeAll
    public static void setUp() {
        ReportExtension.reset();
        TestLauncher.launch(CucumberComparisonTest.class);
        testSuiteResult = ReportExtension.getTestResults().getTestSuiteResults(testSuiteClass(CucumberComparisonTest.class));
    }

    @Test
    void verifyEat5OutOf12() {
        TestCaseResult results = testSuiteResult.getTestCaseResult("eat5OutOf12");

        assertThat(results.getWordify()).isEqualTo(
            "Given i have cucumbers with amount 12 \n" +
            "When i eat cucumbers with amount 5 \n" +
            "Then i should have cucumbers with amount 7");
    }

    /**
     * Below would be good
     */
    @Disabled
    @Test
    void verifyEat5OutOf12_withAlternateWordify() {
        TestCaseResult results = testSuiteResult.getTestCaseResult("eat5OutOf12");

        assertThat(results.getWordify()).isEqualTo(
            "Given i have 12 cucumbers \n" +
            "When i eat 12 cucumbers \n" +
            "Then there are have 12 cucumbers");
    }
}
