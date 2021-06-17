package junit5.results;

import junit5.results.extension.ReportExtension;
import junit5.results.model.TestCaseResult;
import junit5.results.model.TestSuiteResults;
import junit5.results.model.TestSuiteResultsMetadata;
import shared.undertest.AbortedTestCasesUnderTest;
import shared.undertest.FailedDueToExceptionTestCasesUnderTest;
import shared.undertest.ClassUnderTest;
import shared.undertest.DisabledTestCasesUnderTest;
import shared.undertest.FailedTestCasesUnderTest;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static junit5.results.model.TestCaseResultStatus.ABORTED;
import static junit5.results.model.TestCaseResultStatus.FAILED;
import static junit5.results.model.TestCaseResultStatus.PASSED;
import static junit5.results.model.TestSuiteClass.testSuiteResultsId;
import static junit5.results.model.TestSuiteResultsMetadataBuilder.aTestSuiteResultsMetadata;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportExtensionComponentTest {
    @BeforeEach
    void setUp() {
        ReportExtension.reset();
    }

    @Test
    void verifyTestSuiteClass() {
        TestLauncher.launch(ClassUnderTest.class);
        TestSuiteResults testSuiteResults = ReportExtension.getAllResults().getClassNameToTestSuiteResults().get("ClassUnderTest");
        assertThat(testSuiteResults.getTestSuiteClass().getFullyQualifiedName()).isEqualTo("shared.undertest.ClassUnderTest");
        assertThat(testSuiteResults.getTestSuiteClass().getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuiteResults.getTestSuiteClass().getPackageName()).isEqualTo("shared.undertest");
    }

    @Test
    void verifyResultsForPassingTestCases() {
        Class<?> clazz = ClassUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResults, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withPassedCount(4)
            .build();
        assertThat(testSuiteResults.getResultsMetadata()).isEqualTo(metadata);

        // TODO
        // @ValueSource(strings = { "value 1", "value 2", "value 3" })
        // paramTest value 1
        // paramTest value 1, value 2

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        assertThat(testSuiteResults.getCapturedTestMethod("testMethod"))
            .isEqualTo(passedTestMethod());

        List<TestCaseResult> paramTest = testSuiteResults.getCapturedTestMethods("paramTest");
        assertThat(paramTest.get(0)).isEqualTo(passedParamTestCaseResult("Passing assertion with value 1"));
        assertThat(paramTest.get(1)).isEqualTo(passedParamTestCaseResult("Passing assertion with value 2"));
        assertThat(paramTest.get(2)).isEqualTo(passedParamTestCaseResult("Passing assertion with value 3"));
    }

    @Test
    void verifyResultsForDisabledTestCases() {
        Class<?> clazz = DisabledTestCasesUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResults, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(2)
            .withSkippedCount(2)
            .build();
        assertThat(testSuiteResults.getResultsMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest"
        );
    }

    @Test
    void verifyResultsForFailedTestCases() {
        Class<?> clazz = FailedTestCasesUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResults, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withFailedCount(4)
            .build();
        assertThat(testSuiteResults.getResultsMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResults.getCapturedTestMethod("testMethod");
        assertThat(testMethod).isEqualTo(failedTestMethod());
        assertCauseWithMessage(testMethod.getCause().get(), "\n" + "Expecting:\n" + " <true>\n" + "to be equal to:\n" + " <false>\n" + "but was not.");

        List<TestCaseResult> paramTest = testSuiteResults.getCapturedTestMethods("paramTest");
        assertThat(paramTest.get(0)).isEqualTo(failedParamTestCaseResult("Failing assertion with value 1"));
        assertCauseWithMessage(paramTest.get(0).getCause().get(), "\nExpecting:\n <\"value 1\">\nto be equal to:\n <null>\nbut was not.");
        assertThat(paramTest.get(1)).isEqualTo(failedParamTestCaseResult("Failing assertion with value 2"));
        assertCauseWithMessage(paramTest.get(1).getCause().get(), "\nExpecting:\n <\"value 2\">\nto be equal to:\n <null>\nbut was not.");
        assertThat(paramTest.get(2)).isEqualTo(failedParamTestCaseResult("Failing assertion with value 3"));
        assertCauseWithMessage(paramTest.get(2).getCause().get(), "\nExpecting:\n <\"value 3\">\nto be equal to:\n <null>\nbut was not.");
    }

    @Test
    void verifyResultsForFailedDueToNullPointerTestCases() {
        Class<?> clazz = FailedDueToExceptionTestCasesUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResults, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withFailedCount(4)
            .build();
        assertThat(testSuiteResults.getResultsMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResults.getCapturedTestMethod("testMethod");
        assertThat(testMethod).isEqualTo(failedTestMethodDueToException());
        assertNullPointerCause(testMethod.getCause().get());

        List<TestCaseResult> paramTest = testSuiteResults.getCapturedTestMethods("paramTest");
        assertThat(paramTest.get(0)).isEqualTo(failedParamTestCaseResultDueToException("Method that throws a pointer method with value 1"));
        assertNullPointerCause(paramTest.get(0).getCause().get());
        assertThat(paramTest.get(1)).isEqualTo(failedParamTestCaseResultDueToException("Method that throws a pointer method with value 2"));
        assertNullPointerCause(paramTest.get(1).getCause().get());
        assertThat(paramTest.get(2)).isEqualTo(failedParamTestCaseResultDueToException("Method that throws a pointer method with value 3"));
        assertNullPointerCause(paramTest.get(2).getCause().get());
    }

    @Test
    void verifyResultsForAbortedTestCases() {
        Class<?> clazz = AbortedTestCasesUnderTest.class;
        TestSuiteResults testSuiteResults = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResults, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withAbortedCount(4)
            .build();
        assertThat(testSuiteResults.getResultsMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResults.getCapturedTestMethod("testMethod");
        assertThat(testMethod).isEqualTo(abortedTestMethod());
        assertThat(testMethod.getCause()).isPresent();
        assertCauseWithMessage(testMethod.getCause().get(), "Assumption failed: testMethod does not contain Z");

        List<TestCaseResult> paramTest = testSuiteResults.getCapturedTestMethods("paramTest");
        assertThat(paramTest.get(0)).isEqualTo(abortedParamTestCaseResultDueToException("Aborting assertion with value 1"));
        assertCauseWithMessage(paramTest.get(0).getCause().get(), "Assumption failed: value 1 does not contain z");
        assertThat(paramTest.get(1)).isEqualTo(abortedParamTestCaseResultDueToException("Aborting assertion with value 2"));
        assertCauseWithMessage(paramTest.get(1).getCause().get(), "Assumption failed: value 2 does not contain z");
        assertThat(paramTest.get(2)).isEqualTo(abortedParamTestCaseResultDueToException("Aborting assertion with value 3"));
        assertCauseWithMessage(paramTest.get(2).getCause().get(), "Assumption failed: value 3 does not contain z");
    }

    private void assertCauseWithMessage(Throwable cause, String message) {
        assertThat(cause.getMessage()).isEqualTo(message);
        assertThat(cause.getClass()).isNotNull();
        assertThat(cause.getCause()).isNull();
        assertThat(cause.getStackTrace()).isNotNull();
    }

    private void assertNullPointerCause(Throwable cause) {
        assertThat(cause.getMessage()).isNull();
        assertThat(cause.getClass()).isEqualTo(NullPointerException.class);
        assertThat(cause.getCause()).isNull();
        assertThat(cause.getStackTrace()).isNotNull();
    }

    private void assertTestSuitClass(TestSuiteResults testSuiteResults, Class<?> clazz) {
        assertThat(testSuiteResults.getTestSuiteClass().getClassName()).isEqualTo(clazz.getSimpleName());
        assertThat(testSuiteResults.getTestSuiteClass().getFullyQualifiedName()).isEqualTo(clazz.getPackage().getName() + "." + clazz.getSimpleName());
        assertThat(testSuiteResults.getTestSuiteClass().getPackageName()).isEqualTo(clazz.getPackage().getName());
    }

    private TestSuiteResults launchTestSuite(Class<?> clazz) {
        TestLauncher.launch(clazz);
        assertThat(ReportExtension.getAllResults().getClasses()).containsExactly(clazz.getSimpleName());
        return ReportExtension.getAllResults().getClassNameToTestSuiteResults().get(clazz.getSimpleName());
    }

    private TestCaseResult passedParamTestCaseResult(String wordify) {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withWordify(wordify)
            .withStatus(PASSED)
            .withTestSuiteResultsId(testSuiteResultsId(ClassUnderTest.class))
            .build();
    }

    private TestCaseResult failedParamTestCaseResult(String wordify) {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withWordify(wordify)
            .withStatus(FAILED)
            .withTestSuiteResultsId(testSuiteResultsId(FailedTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult failedParamTestCaseResultDueToException(String wordify) {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withWordify(wordify)
            .withStatus(FAILED)
            .withTestSuiteResultsId(testSuiteResultsId(FailedDueToExceptionTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult abortedParamTestCaseResultDueToException(String wordify) {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withWordify(wordify)
            .withStatus(ABORTED)
            .withTestSuiteResultsId(testSuiteResultsId(AbortedTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult passedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withWordify("Passing assertion")
            .withStatus(PASSED)
            .withTestSuiteResultsId(testSuiteResultsId(ClassUnderTest.class))
            .build();
    }

    private TestCaseResult failedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withWordify("Failing assertion")
            .withStatus(FAILED)
            .withTestSuiteResultsId(testSuiteResultsId(FailedTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult failedTestMethodDueToException() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withWordify("Method that throws a pointer method")
            .withStatus(FAILED)
            .withTestSuiteResultsId(testSuiteResultsId(FailedDueToExceptionTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult abortedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withWordify("Aborting assertion")
            .withStatus(ABORTED)
            .withTestSuiteResultsId(testSuiteResultsId(AbortedTestCasesUnderTest.class))
            .build();
    }
}
