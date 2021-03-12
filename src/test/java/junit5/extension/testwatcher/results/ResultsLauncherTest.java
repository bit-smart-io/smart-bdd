package junit5.extension.testwatcher.results;

import junit5.extension.utils.TestLauncher;
import junit5.extension.utils.TestListener;
import org.junit.jupiter.api.Test;
import results.junit.results.ResultsForClass;
import results.junit.results.ResultsForTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static results.junit.results.ResultsForTest.Status.PASSED;

public class ResultsLauncherTest {

    @Test
    void launchTests() {
        ResultsExtension.reset();
        new TestLauncher().launch(new TestListener(), ClassUnderTest.class);

        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly("ClassUnderTest");
        ResultsForClass resultsForClass = ResultsExtension.getTestResultsForClasses().getResultsForClasses().get("ClassUnderTest");
        assertThat(resultsForClass).isNotNull();

        assertThat(resultsForClass.getMethodNames()).contains(
            "firstTest",
            "secondTest",
            "thirdParamTest"
        );

        ResultsForTest firstTest = resultsForClass.getCapturedTestMethod("firstTest");
        assertThat(firstTest.getWordify()).isEqualTo("Assert that \"first test\" is equal to \"first test\"");

        ResultsForTest secondTest = resultsForClass.getCapturedTestMethod("secondTest");
        assertThat(secondTest.getWordify()).isEqualTo("Assert that \"second test\" is equal to \"second test\"");

        List<ResultsForTest> thirdTestResults = resultsForClass.getCapturedTestMethods("thirdParamTest");
        assertThat(thirdTestResults.get(0).getWordify()).isEqualTo("Assert that value 1 is not null");
        assertThat(thirdTestResults.get(0).getStatus()).isEqualTo(PASSED);
        assertThat(thirdTestResults.get(1).getWordify()).isEqualTo("Assert that value 2 is not null");
        assertThat(thirdTestResults.get(1).getStatus()).isEqualTo(PASSED);
        assertThat(thirdTestResults.get(2).getWordify()).isEqualTo("Assert that value 3 is not null");
        assertThat(thirdTestResults.get(2).getStatus()).isEqualTo(PASSED);
    }
}
