package junit5.results;

import junit5.results.model.TestCaseResult;
import junit5.results.model.TestSuiteResults;
import junit5.results.model.TestSuiteResultsId;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static junit5.results.model.TestCaseResult.Status.PASSED;

public class ResultsExtensionComponentTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;
    private static final String CLASS_UNDER_TEST_NAME = "ClassUnderTest";
    private static final String PACKAGE_NAME = "junit5.results";

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void launchTests() {
        TestLauncher.launch(CLASS_UNDER_TEST);

        assertThat(ResultsExtension.getTestResultsForClasses().getClasses()).containsExactly(CLASS_UNDER_TEST_NAME);
        TestSuiteResults testSuiteResults = ResultsExtension.getTestResultsForClasses().getClassNameToClassResults().get(CLASS_UNDER_TEST_NAME);
        assertThat(testSuiteResults).isNotNull();

        assertThat(testSuiteResults.getResultsId().getClassName()).isEqualTo(CLASS_UNDER_TEST_NAME);
        assertThat(testSuiteResults.getResultsId().getName()).isEqualTo(PACKAGE_NAME + "." + CLASS_UNDER_TEST_NAME);
        assertThat(testSuiteResults.getResultsId().getPackageName()).isEqualTo(PACKAGE_NAME);
        assertThat(testSuiteResults.getMethodNames()).contains(
            "firstTest",
            "secondTest",
            "thirdParamTest"
        );

        TestCaseResult firstTest = testSuiteResults.getCapturedTestMethod("firstTest");
        assertThat(firstTest.getWordify()).isEqualTo("Assert that \"first test\" is equal to \"first test\"");

        TestCaseResult secondTest = testSuiteResults.getCapturedTestMethod("secondTest");
        assertThat(secondTest.getWordify()).isEqualTo("Assert that \"second test\" is equal to \"second test\"");

        List<TestCaseResult> thirdTest = testSuiteResults.getCapturedTestMethods("thirdParamTest");
        assertThat(thirdTest.get(0)).isEqualTo(testResult("Assert that value 1 is not null"));
        assertThat(thirdTest.get(1)).isEqualTo(testResult("Assert that value 2 is not null"));
        assertThat(thirdTest.get(2)).isEqualTo(testResult("Assert that value 3 is not null"));
    }

    private TestCaseResult testResult(String wordify) {
        final TestCaseResult testCaseResult = new TestCaseResult("thirdParamTest", new TestSuiteResultsId(CLASS_UNDER_TEST));
        testCaseResult.setWordify(wordify);
        testCaseResult.setStatus(PASSED);
        return testCaseResult;
    }
}
