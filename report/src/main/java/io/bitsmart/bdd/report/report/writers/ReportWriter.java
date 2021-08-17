package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;

/**
 * Will need a strategy - write the results/report and or http post the results/report
 */
public class ReportWriter {
    private final DataReportWriter dataReportWriter = new DataReportWriter(new DataFileNameProvider());
    private final HtmlReportWriter htmlReportWriter = new HtmlReportWriter(new HtmlFileNameProvider());

    /** TODO in transition. Please see TestResults for more details. */
    public void write(TestResults testResults) {
        Report report = ReportFactory.create(testResults);
        dataReportWriter.write(report.getIndex());
        htmlReportWriter.write(report.getIndex());
    }

    /** TODO in transition - duplicates ReportFactory.testSuite */
    public void write(TestSuiteResult testSuiteResult) {
        dataReportWriter.write(ReportFactory.testSuite(testSuiteResult));
    }

    public void prepare() {
        dataReportWriter.prepareDataDirectory();
        htmlReportWriter.prepareDataDirectory();
    }
}
