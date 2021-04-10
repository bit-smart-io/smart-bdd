package ft.report.oneclass;

import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit5.results.ResultsExtension;
import report.model.Report;
import report.model.Status;
import report.model.TestCase;

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

        Report report = ReportFactory.create(ResultsExtension.getAllResults());
        assertThat(report).isNotNull();
        assertThat(report.getTestCases()).hasSize(5);

        assertThat(report.getTestCases()).contains(firstTestResult());
    }

    private TestCase firstTestResult() {
        return aResult()
            .withWordify("Assert that \"first test\" is equal to \"first test\"")
            .withStatus(Status.PASSED)
            .withMethodName("firstTest")
            .withClassName("ClassUnderTest")
            .withPackageName("ft.report.oneclass")
            .build();
    }
}
