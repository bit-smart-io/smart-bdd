package ft.report.oneclass;

import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import junit5.results.ResultsExtension;
import report.model.Report;
import report.model.Status;
import report.model.TestCase;
import report.model.TestSuite;
import shared.undertest.ClassUnderTest;

import static ft.report.builders.TestCaseBuilder.aTestCase;
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
        assertThat(report.getTestCases()).hasSize(4);

        assertThat(report.getTestSuites()).hasSize(1);
        TestSuite testSuite = report.getTestSuites().get(0);
        assertTestSuite(testSuite);
    }

    private void assertTestSuite(TestSuite testSuite) {
        assertThat(testSuite.getName()).isEqualTo("shared.undertest.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("shared.undertest");
        assertThat(testSuite.getMethodNames()).containsExactly("testMethod", "paramTest", "paramTest", "paramTest");
        assertThat(testSuite.getTestCases()).contains(firstTestCase());
    }

    private TestCase firstTestCase() {
        return aTestCase()
            .withWordify("Passing assertion")
            .withStatus(Status.PASSED)
            .withMethodName("testMethod")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .build();
    }
}
