package component.report;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.undertest.ClassUnderTest;
import shared.undertest.FailedDueToExceptionTestCasesUnderTest;
import shared.undertest.FailedTestCasesUnderTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class ReportForPackageTest {
    private static final String PACKAGE_NAME = ClassUnderTest.class.getPackage().getName();

    @BeforeAll
    public static void enableTest() {
        FailedTestCasesUnderTest.setEnabled(true);
        FailedDueToExceptionTestCasesUnderTest.setEnabled(true);
    }

    @AfterAll
    public static void disableTest() {
        FailedTestCasesUnderTest.setEnabled(false);
        FailedDueToExceptionTestCasesUnderTest.setEnabled(false);
    }

    @BeforeEach
    void setUp() {
        ReportExtension.getTestContext().reset();
    }

    @Test
    void reportForOnePackageGeneratedCorrectly() throws IOException {
        TestLauncher.launch(selectPackage(PACKAGE_NAME));

        Report report = ReportFactory.create(ReportExtension.getTestContext().getTestResults());
        TestSuiteLinks testSuiteLinks = report.getIndex().getLinks();
        assertThat(testSuiteLinks.getTestSuites()).contains(
            new TestSuiteNameToFile(
                "shared.undertest.ClassUnderTest",
                "TEST-shared.undertest.ClassUnderTest.json"));
        assertThat(report.getIndex().getSummary()).isEqualTo(new TestSuiteSummary(26,12,4,8,4));
    }
}
