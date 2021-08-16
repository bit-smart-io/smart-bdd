package component.report;

import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import shared.undertest.ClassUnderTest;

import java.io.IOException;
import java.util.Optional;

import static component.report.ReportAssertions.assertPassingTestSuite;
import static component.report.ReportTestUtils.loadTestSuite;
import static component.report.ReportTestUtils.writeReport;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class ReportForPackageTest {
    private static final String PACKAGE_NAME = ClassUnderTest.class.getPackage().getName();
    private static final Class<?> PASSING_CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ReportExtension.reset();
    }

    @Test
    void reportForOnePackageGeneratedCorrectly() throws IOException {
        TestLauncher.launch(selectPackage(PACKAGE_NAME));

        Report report = ReportFactory.create(ReportExtension.getResults());
        TestSuiteLinks testSuiteLinks = report.getHomePage().getLinks();
        assertThat(testSuiteLinks.getTestSuites()).contains(
            new TestSuiteNameToFile(
                "shared.undertest.ClassUnderTest",
                "TEST-shared.undertest.ClassUnderTest.json"));
        assertThat(report.getHomePage().getSummary()).isEqualTo(new TestSuiteSummary(19,5,4,8,4));
        writeReport(report);

        assertReport(report);
        assertPassingTestSuite(loadTestSuite(PASSING_CLASS_UNDER_TEST));
    }

    public static void assertReport(Report report) {
        assertThat(report).isNotNull();
        assertThat(report.getTestCases()).hasSize(19);
        assertThat(report.getTestSuites()).hasSize(6);
        assertThat(getPassingTestSuite(report, PASSING_CLASS_UNDER_TEST.getName())).isPresent();
        getPassingTestSuite(report, PASSING_CLASS_UNDER_TEST.getName())
            .ifPresent(ReportAssertions::assertPassingTestSuite);
    }

    private static Optional<TestSuite> getPassingTestSuite(Report report, String testSuiteName) {
        return report.getTestSuites().stream()
            .filter(testSuite -> testSuite.getName().equals(testSuiteName))
            .findFirst();
    }
}
