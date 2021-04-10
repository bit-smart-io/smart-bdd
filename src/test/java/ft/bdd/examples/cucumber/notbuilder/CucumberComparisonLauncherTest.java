package ft.bdd.examples.cucumber.notbuilder;

import junit5.results.ResultsExtension;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.Test;
import junit5.results.model.TestSuiteResults;
import junit5.results.model.TestCaseResult;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    @Test
    void launchTests() {
        ResultsExtension.reset();
        TestLauncher.launch(CucumberComparisonTest.class);
        assertThat(ResultsExtension.getAllResults().getClasses()).containsExactly("CucumberComparisonTest");

        TestSuiteResults testSuiteResults = ResultsExtension.getAllResults().getClassNameToClassResults().get("CucumberComparisonTest");
        assertThat(testSuiteResults).isNotNull();

        assertThat(testSuiteResults.getMethodNames()).contains(
            "eat5OutOf12"
        );

        TestCaseResult firstTest = testSuiteResults.getCapturedTestMethod("eat5OutOf12");

        assertThat(firstTest.getWordify()).isEqualTo(
            "Given there are cucumbers 12 \n" +
            "When i eat cucumbers 5 \n" +
            "Then i should have cucumbers 7");
    }
}
