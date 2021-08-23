package component.results.scenarios;

import component.results.AbstractResultsForTestSuite;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import org.junit.jupiter.api.Test;
import shared.undertest.ClassUnderTest;

import java.util.List;

import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.PASSED;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadataBuilder.aTestSuiteResultsMetadata;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class PassingResultsTest extends AbstractResultsForTestSuite {

    @Override
    public Class<?> classUnderTest() {
        return ClassUnderTest.class;
    }

    @Test
    void verifyResultsForPassingTestCases() {
        assertTestSuitClass(testSuiteResult(), classUnderTest());

        TestSuiteResultsMetadata metadata = aTestSuiteResultsMetadata()
            .withTestCaseCount(4)
            .withPassedCount(4)
            .build();
        assertThat(testSuiteResult().getMetadata()).isEqualTo(metadata);

        assertThat(testSuiteResult().getMethods()).containsExactlyInAnyOrder(
            method("testMethod"),
            method("paramTest"),
            method("paramTest"),
            method("paramTest")
        );

        assertThat(testSuiteResult().getTestCaseResult(method("testMethod")))
            .isEqualTo(aPassedTestCaseResult());

        List<TestCaseResult> paramTest = testSuiteResult().getTestCaseResults(method("paramTest"));
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

    private TestCaseResultBuilder aPassedParamTestCaseResult() {
        return aTestCaseResult()
            .withMethod(method("paramTest"))
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteClass());
    }

    private TestCaseResult aPassedTestCaseResult() {
        return aTestCaseResult()
            .withMethod(method("testMethod"))
            .withName("testMethod")
            .withWordify("Passing assertion")
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteClass())
            .build();
    }
}
