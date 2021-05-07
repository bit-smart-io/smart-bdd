package junit5.results;

import report.TestSuiteFileService;
import report.model.Report;
import report.model.TestSuiteLinks;
import report.model.TestSuite;
import report.model.TestSuiteNameToFile;

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
