package component.report;

import component.report.builders.ReportBuilder;
import component.report.builders.ReportIndexBuilder;
import component.report.builders.TestCaseBuilder;
import component.report.builders.TestSuiteBuilder;
import component.report.builders.TestSuiteSummaryBuilder;
import io.bitsmart.bdd.report.report.ReportDataWriter;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestSuite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static component.report.ReportTestUtils.loadReportIndex;
import static component.report.ReportTestUtils.loadTestSuite;
import static component.report.builders.ReportIndexBuilder.aReportIndex;
import static component.report.builders.TestCaseBuilder.aTestCase;
import static component.report.builders.TestSuiteBuilder.aTestSuite;
import static component.report.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static component.report.builders.TestSuiteNameToFileBuilder.aTestSuiteNameToFile;
import static component.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class ReportDataWriterTest {

    @BeforeAll
    public static void writeReport() {
        Report report = aDefaultReport().build();
        new ReportDataWriter().write(report);
    }

    @Test
    void writesReportIndex() throws IOException {
        ReportIndex loadedReportIndex = loadReportIndex();
        assertThat(loadedReportIndex).isEqualTo(aDefaultReportIndex().build());
    }

    @Test
    void writesReportTestSuites() throws IOException {
        TestSuite testSuite = loadTestSuite("defaultName");
        assertThat(testSuite).isEqualTo(aDefaultTestSuiteBuilder().build());
    }

    public static ReportBuilder aDefaultReport() {
        return ReportBuilder.aReport()
            .withReportIndex(aDefaultReportIndex())
            .withTestSuites(singletonList(aDefaultTestSuiteBuilder()))
            .withTestCases(singletonList(aDefaultTestCaseBuilder()));
    }

    public static TestSuiteBuilder aDefaultTestSuiteBuilder() {
        return aTestSuite()
            .withName("defaultName")
            .withClassName("defaultClassName")
            .withPackageName("defaultPackageName")
            .withMethodNames(singletonList("defaultMethodName"))
            .withTestCases(singletonList(aDefaultTestCase()))
            .withSummary(aDefaultSummary());
    }

    public static TestCaseBuilder aDefaultTestCaseBuilder() {
        return aTestCase()
            .withName("defaultTestCaseName")
            .withClassName("defaultClassName")
            .withPackageName("defaultPackageName")
            .withMethodName("defaultMethodName")
            .withWordify("defaultWordify")
            .withStatus(Status.PASSED);
    }

    public static TestSuiteSummaryBuilder aDefaultSummary() {
        return aTestSuiteSummary();
    }

    public static TestCaseBuilder aDefaultTestCase() {
        return aTestCase();
    }

    public static ReportIndexBuilder aDefaultReportIndex() {
        return aReportIndex()
            .withLinks(aTestSuiteLinks()
                .withTestSuites(singletonList(aTestSuiteNameToFile().withName("defaultName").withFile("defaultFileName"))))
            .withSummary(aTestSuiteSummary());
    }
}