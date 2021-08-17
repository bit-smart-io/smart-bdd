package component.examples.cucumber.notbuilder;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;
import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    @Test
    void launchTests() {
        ReportExtension.reset();
        TestLauncher.launch(CucumberComparisonTest.class);
        assertThat(ReportExtension.getTestResults().getClasses()).containsExactly(testSuiteClass(CucumberComparisonTest.class));

        TestSuiteResult testSuiteResult = ReportExtension.getTestResults().getTestSuiteResults(testSuiteClass(CucumberComparisonTest.class));
        assertThat(testSuiteResult).isNotNull();

        assertThat(testSuiteResult.getMethodNames()).contains(
            "eat5OutOf12"
        );

        TestCaseResult firstTest = testSuiteResult.getTestCaseResult("eat5OutOf12");

        assertThat(firstTest.getWordify()).isEqualTo(
            "Given there are cucumbers 12 \n" +
            "When i eat cucumbers 5 \n" +
            "Then i should have cucumbers 7");
    }
}
