package component.results.scenarios;

import component.results.AbstractResultsForTestSuite;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import org.junit.jupiter.api.Test;
import shared.undertest.AbortedTestCasesUnderTest;

import java.util.List;

import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.ABORTED;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadataBuilder.aTestSuiteResultsMetadata;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AbortedResultsTest extends AbstractResultsForTestSuite  {

    @Override
    public Class<?> classUnderTest() {
        return AbortedTestCasesUnderTest.class;
    }

    @Test
    void verifyResultsForAbortedTestCases() {
        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withAbortedCount(4)
            .build();
        assertThat(testSuiteResult().getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult().getMethods()).containsExactlyInAnyOrder(
            method("testMethod"),
            method("paramTest"),
            method("paramTest"),
            method("paramTest")
        );

        TestCaseResult testMethod = testSuiteResult().getTestCaseResult(method("testMethod"));
        assertThat(testMethod).isEqualTo(anAbortedTestMethod());
        assertThat(testMethod.getCause()).isPresent();
        assertCauseWithMessage(testMethod, "Assumption failed: testMethod does not contain Z");

        List<TestCaseResult> paramTest = testSuiteResult().getTestCaseResults(method("paramTest"));
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

    private TestCaseResultBuilder anAbortedParamTestCaseResultDueToException() {
        return aTestCaseResult()
            .withMethod(method("paramTest"))
            .withStatus(ABORTED)
            .withTestSuiteClass(testSuiteClass());
    }

    private TestCaseResult anAbortedTestMethod() {
        return aTestCaseResult()
            .withMethod(method("testMethod"))
            .withName("testMethod")
            .withWordify("Aborting assertion")
            .withStatus(ABORTED)
            .withTestSuiteClass(testSuiteClass())
            .build();
    }
}
