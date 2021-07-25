package io.bitsmart.bdd.report.report;

import io.bitsmart.bdd.report.junit5.results.model.Results;
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
    private static ReportDataWriter reportDataWriter = new ReportDataWriter();

    public void write(Results results) {
        Report report = ReportFactory.create(results);
        reportDataWriter.write(report);
    }
}