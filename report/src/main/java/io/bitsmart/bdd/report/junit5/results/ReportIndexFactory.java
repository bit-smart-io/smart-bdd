package io.bitsmart.bdd.report.junit5.results;

import io.bitsmart.bdd.report.report.TestSuiteFileService;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;

import static java.util.stream.Collectors.toList;

public class ReportIndexFactory {
    private static TestSuiteFileService testSuiteFileService = new TestSuiteFileService();

    public static TestSuiteLinks create(Report report) {
        return new TestSuiteLinks(report.getTestSuites().stream().map(ReportIndexFactory::testSuiteNameToFile).collect(toList()));
    }

    private static TestSuiteNameToFile testSuiteNameToFile(TestSuite testSuite) {
        return new TestSuiteNameToFile(testSuite.getName(), testSuiteFileService.outputFile(testSuite).getName());
    }
}
