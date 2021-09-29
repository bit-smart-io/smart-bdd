/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
        htmlReportWriter.write(ReportFactory.testSuite(testSuiteResult));
    }

    public void prepare() {
        dataReportWriter.prepareDataDirectory();
        htmlReportWriter.prepareDataDirectory();
    }
}
