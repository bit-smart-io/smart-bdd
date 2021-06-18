package junit5.results;

import junit5.results.extension.ReportExtension;
import junit5.results.model.TestCaseResult;
import junit5.results.model.TestCaseResultBuilder;
import junit5.results.model.TestSuiteClass;
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

import static java.util.Collections.singletonList;
import static junit5.results.model.ClassSimpleName.classSimpleName;
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
        TestSuiteResults testSuiteResults = ReportExtension.getAllResults().getTestSuiteResults(classSimpleName(ClassUnderTest.class));
        TestSuiteClass testSuiteClass = testSuiteResults.getTestSuiteClass();
        assertThat(testSuiteClass.getFullyQualifiedName()).isEqualTo("shared.undertest.ClassUnderTest");
        assertThat(testSuiteClass.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuiteClass.getPackageName()).isEqualTo("shared.undertest");
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
        assertThat(testSuiteResults.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        assertThat(testSuiteResults.getTestCaseResult("testMethod"))
            .isEqualTo(aPassedTestMethod());

        List<TestCaseResult> paramTest = testSuiteResults.getTestCaseResults("paramTest");
        assertThat(paramTest.get(0)).isEqualTo(
            aPassedParamTestCaseResult()
                .withWordify("Passing assertion with value 1")
                .withArgs(singletonList("value 1"))
                .withName("paramTest value 1")
                .build()
        );
        assertThat(paramTest.get(1)).isEqualTo(
            aPassedParamTestCaseResult()
                .withWordify("Passing assertion with value 2")
                .withArgs(singletonList("value 2"))
                .withName("paramTest value 2")
                .build()
        );
        assertThat(paramTest.get(2)).isEqualTo(
            aPassedParamTestCaseResult()
                .withWordify("Passing assertion with value 3")
                .withArgs(singletonList("value 3"))
                .withName("paramTest value 3")
                .build()
        );
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
        assertThat(testSuiteResults.getMetadata()).isEqualTo(metadata);

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
        assertThat(testSuiteResults.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResults.getTestCaseResult("testMethod");
        assertThat(testMethod).isEqualTo(aFailedTestMethod());
        assertCauseWithMessage(testMethod, "\n" + "Expecting:\n" + " <true>\n" + "to be equal to:\n" + " <false>\n" + "but was not.");

        List<TestCaseResult> paramTest = testSuiteResults.getTestCaseResults("paramTest");
        TestCaseResult paramTest1 = paramTest.get(0);
        TestCaseResult paramTest2 = paramTest.get(1);
        TestCaseResult paramTest3 = paramTest.get(2);

        assertThat(paramTest1).isEqualTo(
            aFailedParamTestCaseResult()
                .withWordify("Failing assertion with value 1")
                .withArgs(singletonList("value 1"))
                .withName("paramTest value 1")
                .build()
        );
        assertCauseWithMessage(paramTest1, "\nExpecting:\n <\"value 1\">\nto be equal to:\n <null>\nbut was not.");

        assertThat(paramTest2).isEqualTo(
            aFailedParamTestCaseResult()
                .withWordify("Failing assertion with value 2")
                .withArgs(singletonList("value 2"))
                .withName("paramTest value 2")
                .build()
        );
        assertCauseWithMessage(paramTest2, "\nExpecting:\n <\"value 2\">\nto be equal to:\n <null>\nbut was not.");

        assertThat(paramTest3).isEqualTo(
            aFailedParamTestCaseResult()
                .withWordify("Failing assertion with value 3")
                .withArgs(singletonList("value 3"))
                .withName("paramTest value 3")
                .build()
        );
        assertCauseWithMessage(paramTest3, "\nExpecting:\n <\"value 3\">\nto be equal to:\n <null>\nbut was not.");
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
        assertThat(testSuiteResults.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResults.getTestCaseResult("testMethod");
        assertThat(testMethod).isEqualTo(aFailedTestMethodDueToException());
        assertNullPointerCause(testMethod);

        List<TestCaseResult> paramTest = testSuiteResults.getTestCaseResults("paramTest");
        TestCaseResult paramTest1 = paramTest.get(0);
        TestCaseResult paramTest2 = paramTest.get(1);
        TestCaseResult paramTest3 = paramTest.get(2);

        assertThat(paramTest1).isEqualTo(
            aFailedParamTestCaseResultDueToException()
                .withWordify("Method that throws a pointer method with value 1")
                .withArgs(singletonList("value 1"))
                .withName("paramTest value 1")
                .build()
        );
        assertNullPointerCause(paramTest1);

        assertThat(paramTest2).isEqualTo(
            aFailedParamTestCaseResultDueToException()
                .withWordify("Method that throws a pointer method with value 2")
                .withArgs(singletonList("value 2"))
                .withName("paramTest value 2")
                .build()
        );
        assertNullPointerCause(paramTest2);

        assertThat(paramTest3).isEqualTo(
            aFailedParamTestCaseResultDueToException()
                .withWordify("Method that throws a pointer method with value 3")
                .withArgs(singletonList("value 3"))
                .withName("paramTest value 3")
                .build()
        );
        assertNullPointerCause(paramTest3);
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
        assertThat(testSuiteResults.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResults.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResults.getTestCaseResult("testMethod");
        assertThat(testMethod).isEqualTo(anAbortedTestMethod());
        assertThat(testMethod.getCause()).isPresent();
        assertCauseWithMessage(testMethod, "Assumption failed: testMethod does not contain Z");

        List<TestCaseResult> paramTest = testSuiteResults.getTestCaseResults("paramTest");
        TestCaseResult paramTest1 = paramTest.get(0);
        TestCaseResult paramTest2 = paramTest.get(1);
        TestCaseResult paramTest3 = paramTest.get(2);

        assertThat(paramTest1).isEqualTo(
            anAbortedParamTestCaseResultDueToException()
                .withWordify("Aborting assertion with value 1")
                .withArgs(singletonList("value 1"))
                .withName("paramTest value 1")
                .build()
        );
        assertCauseWithMessage(paramTest1, "Assumption failed: value 1 does not contain z");

        assertThat(paramTest2).isEqualTo(
            anAbortedParamTestCaseResultDueToException()
                .withWordify("Aborting assertion with value 2")
                .withArgs(singletonList("value 2"))
                .withName("paramTest value 2")
                .build()
        );
        assertCauseWithMessage(paramTest2, "Assumption failed: value 2 does not contain z");

        assertThat(paramTest3).isEqualTo(
            anAbortedParamTestCaseResultDueToException()
                .withWordify("Aborting assertion with value 3")
                .withArgs(singletonList("value 3"))
                .withName("paramTest value 3")
                .build()
        );
        assertCauseWithMessage(paramTest3, "Assumption failed: value 3 does not contain z");
    }

    private void assertCauseWithMessage(TestCaseResult result, String message) {
        Throwable cause = result.getCause().orElseThrow(() -> new RuntimeException("Expected cause"));
        assertThat(cause.getMessage()).isEqualTo(message);
        assertThat(cause.getClass()).isNotNull();
        assertThat(cause.getCause()).isNull();
        assertThat(cause.getStackTrace()).isNotNull();
    }

    private void assertNullPointerCause(TestCaseResult result) {
        Throwable cause = result.getCause().orElseThrow(() -> new RuntimeException("Expected cause"));
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
        assertThat(ReportExtension.getAllResults().getClasses()).containsExactly(classSimpleName(clazz));
        return ReportExtension.getAllResults().getTestSuiteResults(classSimpleName(clazz));
    }

    private TestCaseResultBuilder aPassedParamTestCaseResult() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteResultsId(ClassUnderTest.class));
    }

    private TestCaseResultBuilder aFailedParamTestCaseResult() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteResultsId(FailedTestCasesUnderTest.class));
    }

    private TestCaseResultBuilder aFailedParamTestCaseResultDueToException() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteResultsId(FailedDueToExceptionTestCasesUnderTest.class));
    }

    private TestCaseResultBuilder anAbortedParamTestCaseResultDueToException() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(ABORTED)
            .withTestSuiteClass(testSuiteResultsId(AbortedTestCasesUnderTest.class));
    }

    private TestCaseResult aPassedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Passing assertion")
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteResultsId(ClassUnderTest.class))
            .build();
    }

    private TestCaseResult aFailedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Failing assertion")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteResultsId(FailedTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult aFailedTestMethodDueToException() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Method that throws a pointer method")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteResultsId(FailedDueToExceptionTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult anAbortedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Aborting assertion")
            .withStatus(ABORTED)
            .withTestSuiteClass(testSuiteResultsId(AbortedTestCasesUnderTest.class))
            .build();
    }
}
