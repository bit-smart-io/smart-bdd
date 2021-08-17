package io.bitsmart.bdd.report.report;

import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;

/**
 * Will need a strategy - write the results/report and or http post the results/report
 *
 * <p>
 * if has test TestExecutionListener
 * then TestExecutionListener#testPlanExecutionFinished can write the report
 * else
 * can write the report when called
 * <p>
 * Currently there is not delta results and or updating. Creates a new report.
 */
public class ReportWriter {
    private final ReportDataWriter reportDataWriter = new ReportDataWriter(new FileNameProvider());

    // we need to know what is unique, only write once
    public void write(TestResults testResults) {
        Report report = ReportFactory.create(testResults);
        reportDataWriter.write(report);
    }

    public void write(TestSuiteResult testSuiteResult) {
        reportDataWriter.write(ReportFactory.testSuite(testSuiteResult));
    }

    public void prepare() {
        reportDataWriter.prepareDataDirectory();
    }
}
