package ft.bdd.examples.cucumber.notbuilder;

import junit5.extension.results.ResultsExtension;
import junit5.extension.utils.TestLauncher;
import junit5.extension.utils.TestListener;
import org.junit.jupiter.api.Test;
import results.junit.results.ResultsForClass;
import results.junit.results.ResultsForTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    @Test
    void launchTests() {
        ResultsExtension.reset();
        new TestLauncher().launch(new TestListener(), CucumberComparisonTest.class);
        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly("CucumberComparisonTest");

        ResultsForClass resultsForClass = ResultsExtension.getTestResultsForClasses().getResultsForClasses().get("CucumberComparisonTest");
        assertThat(resultsForClass).isNotNull();

        assertThat(resultsForClass.getMethodNames()).contains(
            "eat5OutOf12"
        );

        ResultsForTest firstTest = resultsForClass.getCapturedTestMethod("eat5OutOf12");

        assertThat(firstTest.getWordify()).isEqualTo(
            "Given there are cucumbers 12 \n" +
            "When i eat cucumbers 5 \n" +
            "Then i should have cucumbers 7");
    }
}
