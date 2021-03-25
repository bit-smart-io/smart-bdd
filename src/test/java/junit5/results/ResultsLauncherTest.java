package junit5.results;

import junit5.utils.TestLauncher;
import junit5.utils.TestListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.Results;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static junit5.results.ResultsForTest.Status.PASSED;

public class ResultsLauncherTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;
    private static final String CLASS_UNDER_TEST_NAME = "ClassUnderTest";

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void launchTests() {
        new TestLauncher().launch(new TestListener(), CLASS_UNDER_TEST);

        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly(CLASS_UNDER_TEST_NAME);
        ResultsForClass resultsForClass = ResultsExtension.getTestResultsForClasses().getResultsForClasses().get(CLASS_UNDER_TEST_NAME);
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

    @Test
    void componentTest() {
        new TestLauncher().launch(new TestListener(), CLASS_UNDER_TEST);

        // WIP - below is not in order
        Results results = ReportFactory.create(ResultsExtension.getTestResultsForClasses());
        assertThat(results).isNotNull();
        assertThat(results.getResults()).hasSize(5);

    }
}
