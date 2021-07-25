package io.bitsmart.bdd.report.report;

import io.bitsmart.bdd.report.report.model.TestSuite;
import java.io.File;
import static java.lang.System.getProperty;

public class TestSuiteFileUtil {
    private static final String REPORT_TEMP_FOLDER = "io.bitsmart.bdd.report/data/";

    public File outputDirectory() {
        return new File(getProperty("java.io.tmpdir"), REPORT_TEMP_FOLDER);
    }

    public File outputFileForReportIndex() {
        return new File(outputDirectory(), "index.json");
    }

    public File outputFile(TestSuite testSuite) {
        return outputFile(fullyQualifiedName(testSuite));
    }

    private String fullyQualifiedName(TestSuite testSuite) {
        return testSuite.getName();
    }

    private File outputFile(String testName) {
        return new File(outputDirectory(), "TEST-" + testName + ".json");
    }
}
