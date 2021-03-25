package ft.bdd.examples.cucumber.notbuilder;

import report.ResultsExtension;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.Test;
import junit5.results.ClassResults;
import junit5.results.TestResult;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    @Test
    void launchTests() {
        ResultsExtension.reset();
        TestLauncher.launch(CucumberComparisonTest.class);
        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly("CucumberComparisonTest");

        ClassResults classResults = ResultsExtension.getTestResultsForClasses().getClassNameToClassResults().get("CucumberComparisonTest");
        assertThat(classResults).isNotNull();

        assertThat(classResults.getMethodNames()).contains(
            "eat5OutOf12"
        );

        TestResult firstTest = classResults.getCapturedTestMethod("eat5OutOf12");

        assertThat(firstTest.getWordify()).isEqualTo(
            "Given there are cucumbers 12 \n" +
            "When i eat cucumbers 5 \n" +
            "Then i should have cucumbers 7");
    }
}
