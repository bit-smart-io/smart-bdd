package component.results.scenarios;

import component.results.AbstractResultsForTestSuite;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import org.junit.jupiter.api.Test;
import shared.undertest.ClassUnderTest;

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
        assertThat(testSuiteResult().getMetadata()).isEqualTo(
            aTestSuiteResultsMetadata()
                .withTestCaseCount(4)
                .withPassedCount(4)
                .build()
        );

        assertThat(testSuiteResult().getMethods()).containsExactlyInAnyOrder(
            method("testMethod"),
            method("paramTest"),
            method("paramTest"),
            method("paramTest")
        );

        assertThat(testSuiteResult().getTestCaseResult(method("testMethod"))).isEqualTo(aPassedTestCaseResult());

        assertThat(firstTestCaseResult("paramTest")).isEqualTo(
            aPassedParamTestCaseResult()
                .withWordify("Passing assertion with value 1")
                .withArgs(singletonList("value 1"))
                .withName("paramTest value 1")
                .build()
        );
        assertThat(secondTestCaseResult("paramTest")).isEqualTo(
            aPassedParamTestCaseResult()
                .withWordify("Passing assertion with value 2")
                .withArgs(singletonList("value 2"))
                .withName("paramTest value 2")
                .build()
        );
        assertThat(thirdTestCaseResult("paramTest")).isEqualTo(
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
            .withTestSuiteClass(testSuiteClass())
            .withNotes(new Notes());
    }

    private TestCaseResult aPassedTestCaseResult() {
        return aTestCaseResult()
            .withMethod(method("testMethod"))
            .withName("testMethod")
            .withWordify("Passing assertion")
            .withStatus(PASSED)
            .withTestSuiteClass(testSuiteClass())
            .withNotes(new Notes())
            .build();
    }
}
