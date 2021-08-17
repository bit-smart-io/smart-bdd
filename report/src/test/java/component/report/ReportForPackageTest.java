package component.report;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.undertest.ClassUnderTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class ReportForPackageTest {
    private static final String PACKAGE_NAME = ClassUnderTest.class.getPackage().getName();

    @BeforeEach
    void setUp() {
        ReportExtension.reset();
    }

    @Test
    void reportForOnePackageGeneratedCorrectly() throws IOException {
        TestLauncher.launch(selectPackage(PACKAGE_NAME));

        Report report = ReportFactory.create(ReportExtension.getTestResults());
        TestSuiteLinks testSuiteLinks = report.getIndex().getLinks();
        assertThat(testSuiteLinks.getTestSuites()).contains(
            new TestSuiteNameToFile(
                "shared.undertest.ClassUnderTest",
                "TEST-shared.undertest.ClassUnderTest.json"));
        assertThat(report.getIndex().getSummary()).isEqualTo(new TestSuiteSummary(19,5,4,8,4));
    }
}
