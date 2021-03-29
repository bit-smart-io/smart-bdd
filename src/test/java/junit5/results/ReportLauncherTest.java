package junit5.results;

import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.ResultsExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static junit5.results.TestResult.Status.PASSED;

public class ReportLauncherTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;
    private static final String CLASS_UNDER_TEST_NAME = "ClassUnderTest";

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void launchTests() {
        TestLauncher.launch(CLASS_UNDER_TEST);

        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly(CLASS_UNDER_TEST_NAME);
        ClassResults classResults = ResultsExtension.getTestResultsForClasses().getClassNameToClassResults().get(CLASS_UNDER_TEST_NAME);
        assertThat(classResults).isNotNull();

        assertThat(classResults.getMethodNames()).contains(
            "firstTest",
            "secondTest",
            "thirdParamTest"
        );

        TestResult firstTest = classResults.getCapturedTestMethod("firstTest");
        assertThat(firstTest.getWordify()).isEqualTo("Assert that \"first test\" is equal to \"first test\"");

        TestResult secondTest = classResults.getCapturedTestMethod("secondTest");
        assertThat(secondTest.getWordify()).isEqualTo("Assert that \"second test\" is equal to \"second test\"");

        List<TestResult> thirdTestResults = classResults.getCapturedTestMethods("thirdParamTest");
        assertThat(thirdTestResults.get(0)).isEqualTo(testResult("Assert that value 1 is not null"));
        assertThat(thirdTestResults.get(1)).isEqualTo(testResult("Assert that value 2 is not null"));
        assertThat(thirdTestResults.get(2)).isEqualTo(testResult("Assert that value 3 is not null"));
    }

    private TestResult testResult(String wordify) {
        final TestResult testResult = new TestResult("thirdParamTest", CLASS_UNDER_TEST_NAME, "junit5.results");
        testResult.setWordify(wordify);
        testResult.setStatus(PASSED);
        return testResult;
    }
}
