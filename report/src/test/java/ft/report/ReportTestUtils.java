package ft.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bitsmart.bdd.report.report.ReportDataWriter;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

public class ReportTestUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void writeReport(Report report) {
        ReportDataWriter reportDataWriter = new ReportDataWriter();
        reportDataWriter.write(report);
    }

    public static TestSuite loadTestSuite(Class<?> clazz) throws IOException {
        String contents = new FileLoader().toString(testSuiteFile(clazz));
        return MAPPER.readValue(contents, TestSuite.class);
    }

    public static TestSuite loadTestSuite(String clazz) throws IOException {
        String contents = new FileLoader().toString(testSuiteFile(clazz));
        return MAPPER.readValue(contents, TestSuite.class);
    }

    public static ReportIndex loadReportIndex() throws IOException {
        String contents = new FileLoader().toString(homePageFile());
        return MAPPER.readValue(contents, ReportIndex.class);
    }

    public static File homePageFile() {
        return new File(outputDirectory(), "index.json");
    }

    public static File testSuiteFile(Class<?> clazz) {
        return new File(outputDirectory(), "TEST-" + clazz.getName() + ".json");
    }

    public static File testSuiteFile(String clazz) {
        return new File(outputDirectory(), "TEST-" + clazz + ".json");
    }

    public static File outputDirectory() {
        return new File(getProperty("java.io.tmpdir"));
    }
}
