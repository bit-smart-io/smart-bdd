package junit5.extension.testwatcher.results;

import junit5.extension.testwatcher.TestLauncher;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import results.ResultsForClass;
import results.ResultsForTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsLauncherTest {

    @Test
    void launchTests() {
        TestLauncher launcher = new TestLauncher();
        TestListener testListener = new TestListener();
        launcher.launch(testListener, ClassUnderTest.class);
        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly("ClassUnderTest");

        ResultsForClass resultsForClass = ResultsExtension.getTestResultsForClasses().getResultsForClasses().get("ClassUnderTest");
        assertThat(resultsForClass).isNotNull();

        assertThat(resultsForClass.getMethodNames()).contains(
            "firstTest",
            "secondTest",
            "thirdParamTest"
        );

        ResultsForTest firstTest = resultsForClass.getCapturedTestMethod("firstTest");
        assertThat(firstTest.getWordify()).isEqualTo("assert that \"first test\" is equal to \"first test\"");

        ResultsForTest secondTest = resultsForClass.getCapturedTestMethod("secondTest");
        assertThat(secondTest.getWordify()).isEqualTo("assert that \"second test\" is equal to \"second test\"");
        
        List<ResultsForTest> thirdTestResults = resultsForClass.getCapturedTestMethods("thirdParamTest");
        assertThat(thirdTestResults.get(0).getWordify()).isEqualTo("assert that value 1 is not null");
        assertThat(thirdTestResults.get(1).getWordify()).isEqualTo("assert that value 2 is not null");
        assertThat(thirdTestResults.get(2).getWordify()).isEqualTo("assert that value 3 is not null");
    }

    static class TestListener implements TestExecutionListener {
        @Override
        public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
            if (testIdentifier.isTest()) {
                if (testExecutionResult.getStatus() == Status.SUCCESSFUL) {
                    // System.out.println("TEST SUCCESSFUL >>>> " + testIdentifier);
                }
            } else {
                    // System.out.println("CONTAINER >>>> " + testIdentifier);
            }
        }
    }
}
