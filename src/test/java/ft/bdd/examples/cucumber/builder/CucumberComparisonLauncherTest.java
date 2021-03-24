package ft.bdd.examples.cucumber.builder;

import junit5.extension.results.ResultsExtension;
import junit5.extension.utils.TestLauncher;
import junit5.extension.utils.TestListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import junit5.results.ResultsForClass;
import junit5.results.ResultsForTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberComparisonLauncherTest {

    private static ResultsForClass resultsForClass;

    @BeforeAll
    public static void setUp() {
        ResultsExtension.reset();
        new TestLauncher().launch(new TestListener(), CucumberComparisonTest.class);
        resultsForClass = ResultsExtension.getTestResultsForClasses().getResultsForClasses().get("CucumberComparisonTest");
    }

    @Test
    void verifyEat5OutOf12() {
        ResultsForTest results = resultsForClass.getCapturedTestMethod("eat5OutOf12");

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
        ResultsForTest results = resultsForClass.getCapturedTestMethod("eat5OutOf12");

        assertThat(results.getWordify()).isEqualTo(
            "Given i have 12 cucumbers \n" +
            "When i eat 12 cucumbers \n" +
            "Then there are have 12 cucumbers");
    }
}
