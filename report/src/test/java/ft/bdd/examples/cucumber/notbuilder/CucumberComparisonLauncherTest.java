package ft.bdd.examples.cucumber.notbuilder;

import junit5.results.extension.ReportExtension;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.Test;
import junit5.results.model.TestSuiteResult;
import junit5.results.model.TestCaseResult;

import static junit5.results.model.ClassSimpleName.classSimpleName;
import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    @Test
    void launchTests() {
        ReportExtension.reset();
        TestLauncher.launch(CucumberComparisonTest.class);
        assertThat(ReportExtension.getResults().getClasses()).containsExactly(classSimpleName(CucumberComparisonTest.class));

        TestSuiteResult testSuiteResult = ReportExtension.getResults().getTestSuiteResults(classSimpleName(CucumberComparisonTest.class));
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
