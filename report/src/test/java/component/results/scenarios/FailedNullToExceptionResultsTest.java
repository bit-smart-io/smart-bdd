package component.results.scenarios;

import component.results.AbstractResultsForTestSuite;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import org.junit.jupiter.api.Test;
import shared.undertest.FailedDueToExceptionTestCasesUnderTest;

import java.util.List;

import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.FAILED;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadataBuilder.aTestSuiteResultsMetadata;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class FailedNullToExceptionResultsTest extends AbstractResultsForTestSuite  {

    @Override
    public Class<?> classUnderTest() {
        return FailedDueToExceptionTestCasesUnderTest.class;
    }

    @Test
    void verifyResultsForFailedDueToNullPointerTestCases() {
        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withFailedCount(4)
            .build();
        assertThat(testSuiteResult().getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult().getMethods()).containsExactlyInAnyOrder(
            method("testMethod"),
            method("paramTest"),
            method("paramTest"),
            method("paramTest")
        );

        TestCaseResult testMethod = testSuiteResult().getTestCaseResult(method("testMethod"));
        assertEqualsIgnoringCause(testMethod, aFailedTestMethodDueToException());
        assertNullPointerCause(testMethod);

        List<TestCaseResult> paramTest = testSuiteResult().getTestCaseResults(method("paramTest"));
        TestCaseResult paramTest1 = paramTest.get(0);
        TestCaseResult paramTest2 = paramTest.get(1);
        TestCaseResult paramTest3 = paramTest.get(2);

        assertEqualsIgnoringCause(paramTest1,
            aFailedParamTestCaseResultDueToException()
                .withWordify("Method that throws a pointer method with value 1")
                .withArgs(singletonList("value 1"))
                .withName("paramTest value 1")
                .build()
        );
        assertNullPointerCause(paramTest1);

        assertEqualsIgnoringCause(paramTest2,
            aFailedParamTestCaseResultDueToException()
                .withWordify("Method that throws a pointer method with value 2")
                .withArgs(singletonList("value 2"))
                .withName("paramTest value 2")
                .build()
        );
        assertNullPointerCause(paramTest2);

        assertEqualsIgnoringCause(paramTest3,
            aFailedParamTestCaseResultDueToException()
                .withWordify("Method that throws a pointer method with value 3")
                .withArgs(singletonList("value 3"))
                .withName("paramTest value 3")
                .build()
        );
        assertNullPointerCause(paramTest3);
    }

    private void assertNullPointerCause(TestCaseResult result) {
        Throwable cause = result.getCause().orElseThrow(() -> new RuntimeException("Expected cause"));
        assertThat(cause.getMessage()).isNull();
        assertThat(cause.getClass()).isEqualTo(NullPointerException.class);
        assertThat(cause.getCause()).isNull();
        assertThat(cause.getStackTrace()).isNotNull();
    }

    private TestCaseResultBuilder aFailedParamTestCaseResultDueToException() {
        return aTestCaseResult()
            .withMethod(method("paramTest"))
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteClass());
    }

    private TestCaseResult aFailedTestMethodDueToException() {
        return aTestCaseResult()
            .withMethod(method("testMethod"))
            .withName("testMethod")
            .withWordify("Method that throws a pointer method")
            .withStatus(FAILED)
            .withTestSuiteClass(testSuiteClass())
            .build();
    }
}
