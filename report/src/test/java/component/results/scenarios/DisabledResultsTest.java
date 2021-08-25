package component.results.scenarios;

import component.results.AbstractResultsForTestSuite;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import org.junit.jupiter.api.Test;
import shared.undertest.DisabledTestCasesUnderTest;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadataBuilder.aTestSuiteResultsMetadata;
import static org.assertj.core.api.Assertions.assertThat;

public class DisabledResultsTest extends AbstractResultsForTestSuite {

    @Override
    public Class<?> classUnderTest() {
        return DisabledTestCasesUnderTest.class;
    }

    @Test
    void verifyResultsForDisabledTestCases() {
        assertThat(testSuiteResult().getMetadata()).isEqualTo(
            aTestSuiteResultsMetadata()
                .withTestCaseCount(2)
                .withSkippedCount(2)
                .build());

        assertThat(testSuiteResult().getMethods()).containsExactlyInAnyOrder(
            method("testMethod"),
            method("paramTest")
        );
    }
}
