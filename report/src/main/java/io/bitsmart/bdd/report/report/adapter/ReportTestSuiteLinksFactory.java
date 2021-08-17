package io.bitsmart.bdd.report.report.adapter;

import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.report.writers.FileNameProvider;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportTestSuiteLinksFactory {
    private final FileNameProvider fileNameProvider;

    public ReportTestSuiteLinksFactory(FileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    public TestSuiteLinks create(List<TestSuite> testSuites) {
        return new TestSuiteLinks(testSuites.stream().map(this::testSuiteNameToFile).collect(toList()));
    }

    private TestSuiteNameToFile testSuiteNameToFile(TestSuite testSuite) {
        return new TestSuiteNameToFile(testSuite.getName(), fileNameProvider.file(testSuite).getFileName().toString());
    }
}
