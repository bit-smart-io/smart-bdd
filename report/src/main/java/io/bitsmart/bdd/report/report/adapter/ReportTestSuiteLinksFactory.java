package io.bitsmart.bdd.report.report.adapter;

import io.bitsmart.bdd.report.report.TestSuiteFileUtil;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;

import static java.util.stream.Collectors.toList;

public class ReportTestSuiteLinksFactory {
    private static TestSuiteFileUtil testSuiteFileUtil = new TestSuiteFileUtil();

    public static TestSuiteLinks create(Report report) {
        return new TestSuiteLinks(report.getTestSuites().stream().map(ReportTestSuiteLinksFactory::testSuiteNameToFile).collect(toList()));
    }

    private static TestSuiteNameToFile testSuiteNameToFile(TestSuite testSuite) {
        return new TestSuiteNameToFile(testSuite.getName(), testSuiteFileUtil.outputFile(testSuite).getName());
    }
}
