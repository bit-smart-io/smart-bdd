package junit5.results;

import junit5.results.model.TestCaseResult;
import junit5.results.model.TestSuiteResults;
import junit5.results.model.TestSuiteResultsId;
import junit5.results.undertest.ClassUnderTest;
import junit5.results.undertest.DisabledTestCasesUnderTest;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static junit5.results.model.TestCaseStatus.PASSED;

public class ResultsExtensionComponentTest {
    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void resultsIdContainsCorrectClassInformation() {
        TestLauncher.launch(ClassUnderTest.class);
        TestSuiteResults testSuiteResults = ResultsExtension.getAllResults().getClassNameToClassResults().get("ClassUnderTest");
        assertThat(testSuiteResults.getResultsId().getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuiteResults.getResultsId().getName()).isEqualTo("junit5.results.undertest.ClassUnderTest");
        assertThat(testSuiteResults.getResultsId().getPackageName()).isEqualTo("junit5.results.undertest");
    }

    @Test
    void resultsForPassingTestCases() {
        Class<?> clazz = ClassUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertResultsId(testSuiteResults, clazz);

        assertThat(testSuiteResults.getResultsMetadata().getTestCaseCount()).isEqualTo(4);
        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult firstTest = testSuiteResults.getCapturedTestMethod("testMethod");
        assertThat(firstTest.getWordify()).isEqualTo("Assert that \"test method\" is equal to \"test method\"");

        List<TestCaseResult> thirdTest = testSuiteResults.getCapturedTestMethods("paramTest");
        assertThat(thirdTest.get(0)).isEqualTo(passingParamTestCaseResult("Assert that value 1 is not null"));
        assertThat(thirdTest.get(1)).isEqualTo(passingParamTestCaseResult("Assert that value 2 is not null"));
        assertThat(thirdTest.get(2)).isEqualTo(passingParamTestCaseResult("Assert that value 3 is not null"));
    }

    @Test
    void resultsForDisabledTestCases() {
        Class<?> clazz = DisabledTestCasesUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertResultsId(testSuiteResults, clazz);

        assertThat(testSuiteResults.getResultsMetadata().getTestCaseCount()).isEqualTo(2);
        //assertThat(testSuiteResults.getResultsMetadata().getSkippedCount()).isEqualTo(2);
        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest"
        );

    }

    private void assertResultsId(TestSuiteResults testSuiteResults, Class<?> clazz) {
        assertThat(testSuiteResults.getResultsId().getClassName()).isEqualTo(clazz.getSimpleName());
        assertThat(testSuiteResults.getResultsId().getName()).isEqualTo(clazz.getPackage().getName() + "." + clazz.getSimpleName());
        assertThat(testSuiteResults.getResultsId().getPackageName()).isEqualTo(clazz.getPackage().getName());
    }

    private TestSuiteResults launchTestSuite(Class<?> clazz) {
        TestLauncher.launch(clazz);
        assertThat(ResultsExtension.getAllResults().getClasses()).containsExactly(clazz.getSimpleName());
        return ResultsExtension.getAllResults().getClassNameToClassResults().get(clazz.getSimpleName());
    }

    //TODO create builders for these!
    private TestCaseResult passingParamTestCaseResult(String wordify) {
        final TestCaseResult testCaseResult = new TestCaseResult("paramTest", new TestSuiteResultsId(ClassUnderTest.class));
        testCaseResult.setWordify(wordify);
        testCaseResult.setStatus(PASSED);
        return testCaseResult;
    }
}
