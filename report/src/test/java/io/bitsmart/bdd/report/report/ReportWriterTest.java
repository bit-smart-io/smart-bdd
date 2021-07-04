package io.bitsmart.bdd.report.report;

import ft.report.builders.ReportIndexBuilder;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.ReportIndex;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static ft.report.ReportTestUtils.loadReportIndex;
import static ft.report.builders.ReportIndexBuilder.aReportIndex;
import static ft.report.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static ft.report.builders.TestSuiteNameToFileBuilder.aTestSuiteNameToFile;
import static ft.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class ReportWriterTest {

    @Test
    void writesReport() throws IOException {
        ReportIndex reportIndex = aDefaultReportIndex().build();

        Report report = new Report();
        report.setReportIndex(reportIndex);

        ReportWriter reportWriter = new ReportWriter();
        reportWriter.write(report);

        ReportIndex loadedReportIndex = loadReportIndex();
        assertThat(loadedReportIndex).isNotNull();
    }

    public ReportIndexBuilder aDefaultReportIndex() {
        return aReportIndex()
            .withLinks(aTestSuiteLinks()
                .withTestSuites(singletonList(aTestSuiteNameToFile().withName("defaultName").withFile("defaultFileName"))))
            .withSummary(aTestSuiteSummary());
    }
}