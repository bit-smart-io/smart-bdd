package io.bitsmart.bdd.report.report.adapter;

import io.bitsmart.bdd.report.report.TestSuiteFileUtil;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportTestSuiteLinksFactory {
    private static TestSuiteFileUtil testSuiteFileUtil = new TestSuiteFileUtil();

    public static TestSuiteLinks create(List<TestSuite> testSuites) {
        return new TestSuiteLinks(testSuites.stream().map(ReportTestSuiteLinksFactory::testSuiteNameToFile).collect(toList()));
    }

    private static TestSuiteNameToFile testSuiteNameToFile(TestSuite testSuite) {
        return new TestSuiteNameToFile(testSuite.getName(), testSuiteFileUtil.outputFile(testSuite).getName());
    }
}
