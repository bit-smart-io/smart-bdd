package ft.bdd.examples.cucumber.notbuilder;

import junit5.results.ResultsExtension;
import junit5.utils.TestLauncher;
import junit5.utils.TestListener;
import org.junit.jupiter.api.Test;
import junit5.results.ResultsForClass;
import junit5.results.ResultsForTest;

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
