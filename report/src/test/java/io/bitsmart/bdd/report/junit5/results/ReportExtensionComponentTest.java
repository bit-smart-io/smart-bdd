package io.bitsmart.bdd.report.junit5.results;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import shared.undertest.AbortedTestCasesUnderTest;
import shared.undertest.FailedDueToExceptionTestCasesUnderTest;
import shared.undertest.ClassUnderTest;
import shared.undertest.DisabledTestCasesUnderTest;
import shared.undertest.FailedTestCasesUnderTest;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static io.bitsmart.bdd.report.junit5.results.model.ClassSimpleName.classSimpleName;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.ABORTED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.FAILED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.PASSED;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadataBuilder.aTestSuiteResultsMetadata;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportExtensionComponentTest {
    @BeforeEach
    void setUp() {
        ReportExtension.reset();
    }

    @Test
    void verifyTestSuiteClass() {
        TestLauncher.launch(ClassUnderTest.class);
        TestSuiteResult testSuiteResult = ReportExtension.getTestResults().getTestSuiteResults(classSimpleName(ClassUnderTest.class));
        TestSuiteClass testSuiteClass = testSuiteResult.getTestSuiteClass();
        assertThat(testSuiteClass.getFullyQualifiedName()).isEqualTo("shared.undertest.ClassUnderTest");
        assertThat(testSuiteClass.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuiteClass.getPackageName()).isEqualTo("shared.undertest");
    }

    @Test
    void verifyResultsForPassingTestCases() {
        Class<?> clazz = ClassUnderTest.class;
        TestSuiteResult testSuiteResult = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResult, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withPassedCount(4)
            .build();
        assertThat(testSuiteResult.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        assertThat(testSuiteResult.getTestCaseResult("testMethod"))
            .isEqualTo(aPassedTestMethod());

        List<TestCaseResult> paramTest = testSuiteResult.getTestCaseResults("paramTest");
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
        TestSuiteResult testSuiteResult = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResult, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(2)
            .withSkippedCount(2)
            .build();
        assertThat(testSuiteResult.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest"
        );
    }

    @Test
    void verifyResultsForFailedTestCases() {
        Class<?> clazz = FailedTestCasesUnderTest.class;
        TestSuiteResult testSuiteResult = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResult, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withFailedCount(4)
            .build();
        assertThat(testSuiteResult.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResult.getTestCaseResult("testMethod");
        assertThat(testMethod).isEqualTo(aFailedTestMethod());
        assertCauseWithMessage(testMethod, "\n" + "Expecting:\n" + " <true>\n" + "to be equal to:\n" + " <false>\n" + "but was not.");

        List<TestCaseResult> paramTest = testSuiteResult.getTestCaseResults("paramTest");
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
        TestSuiteResult testSuiteResult = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResult, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withFailedCount(4)
            .build();
        assertThat(testSuiteResult.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResult.getTestCaseResult("testMethod");
        assertThat(testMethod).isEqualTo(aFailedTestMethodDueToException());
        assertNullPointerCause(testMethod);

        List<TestCaseResult> paramTest = testSuiteResult.getTestCaseResults("paramTest");
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
        TestSuiteResult testSuiteResult = launchTestSuite(clazz);
        assertTestSuitClass(testSuiteResult, clazz);

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withAbortedCount(4)
            .build();
        assertThat(testSuiteResult.getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult.getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest"
        );

        TestCaseResult testMethod = testSuiteResult.getTestCaseResult("testMethod");
        assertThat(testMethod).isEqualTo(anAbortedTestMethod());
        assertThat(testMethod.getCause()).isPresent();
        assertCauseWithMessage(testMethod, "Assumption failed: testMethod does not contain Z");

        List<TestCaseResult> paramTest = testSuiteResult.getTestCaseResults("paramTest");
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

    private void assertTestSuitClass(TestSuiteResult testSuiteResult, Class<?> clazz) {
        assertThat(testSuiteResult.getTestSuiteClass().getClassName()).isEqualTo(clazz.getSimpleName());
        assertThat(testSuiteResult.getTestSuiteClass().getFullyQualifiedName()).isEqualTo(clazz.getPackage().getName() + "." + clazz.getSimpleName());
        assertThat(testSuiteResult.getTestSuiteClass().getPackageName()).isEqualTo(clazz.getPackage().getName());
    }

    private TestSuiteResult launchTestSuite(Class<?> clazz) {
        TestLauncher.launch(clazz);
        assertThat(ReportExtension.getTestResults().getClasses()).containsExactly(classSimpleName(clazz));
        return ReportExtension.getTestResults().getTestSuiteResults(classSimpleName(clazz));
    }

    private TestCaseResultBuilder aPassedParamTestCaseResult() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteClass(ClassUnderTest.class));
    }

    private TestCaseResultBuilder aFailedParamTestCaseResult() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteClass(FailedTestCasesUnderTest.class));
    }

    private TestCaseResultBuilder aFailedParamTestCaseResultDueToException() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteClass(FailedDueToExceptionTestCasesUnderTest.class));
    }

    private TestCaseResultBuilder anAbortedParamTestCaseResultDueToException() {
        return aTestCaseResult()
            .withMethodName("paramTest")
            .withStatus(ABORTED)
            .withTestSuiteClass(testSuiteClass(AbortedTestCasesUnderTest.class));
    }

    private TestCaseResult aPassedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Passing assertion")
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteClass(ClassUnderTest.class))
            .build();
    }

    private TestCaseResult aFailedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Failing assertion")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteClass(FailedTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult aFailedTestMethodDueToException() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Method that throws a pointer method")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteClass(FailedDueToExceptionTestCasesUnderTest.class))
            .build();
    }

    private TestCaseResult anAbortedTestMethod() {
        return aTestCaseResult()
            .withMethodName("testMethod")
            .withName("testMethod")
            .withWordify("Aborting assertion")
            .withStatus(ABORTED)
            .withTestSuiteClass(testSuiteClass(AbortedTestCasesUnderTest.class))
            .build();
    }
}
