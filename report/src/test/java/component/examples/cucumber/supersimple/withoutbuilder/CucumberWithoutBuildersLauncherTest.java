package component.examples.cucumber.supersimple.withoutbuilder;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;
import static org.assertj.core.api.Assertions.assertThat;

public class CucumberWithoutBuildersLauncherTest {

    @Test
    void launchTests() {
        ReportExtension.getTestContext().reset();
        TestLauncher.launch(CucumberWithoutBuildersTest.class);
        assertThat(ReportExtension.getTestContext().getTestResults().getClasses()).containsExactly(testSuiteClass(CucumberWithoutBuildersTest.class));

        TestSuiteResult testSuiteResult = ReportExtension.getTestContext().getTestResults().getTestSuiteResults(testSuiteClass(CucumberWithoutBuildersTest.class));
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
