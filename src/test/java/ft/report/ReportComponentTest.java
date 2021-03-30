package ft.report;

import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.ResultsExtension;
import report.model.Report;
import report.model.Status;
import report.model.TestResult;

import static ft.report.ResultBuilder.aResult;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportComponentTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void createReport() {
        TestLauncher.launch(CLASS_UNDER_TEST);

        Report report = ReportFactory.create(ResultsExtension.getTestResultsForClasses());
        assertThat(report).isNotNull();
        assertThat(report.getTestResults()).hasSize(5);

        assertThat(report.getTestResults()).contains(firstTestResult());
    }

    private TestResult firstTestResult() {
        return aResult()
            .withWordify("Assert that \"first test\" is equal to \"first test\"")
            .withStatus(Status.PASSED)
            .withMethodName("firstTest")
            .withClassName("ClassUnderTest")
            .withPackageName("ft.report")
            .build();
    }
}
