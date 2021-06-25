package ft.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bitsmart.bdd.report.report.ReportWriter;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

public class ReportTestUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static TestSuite loadTestSuite(Class<?> clazz) throws IOException {
        String contents = new FileLoader().toString(outputFile(clazz.getName()));
        return MAPPER.readValue(contents, TestSuite.class);
    }

    public static void writeReport(Report report) {
        ReportWriter reportWriter = new ReportWriter();
        reportWriter.write(report);
    }

    public static File outputFile(String testName) {
        return new File(outputDirectory(), "TEST-" + testName + ".json");
    }

    public static File outputDirectory() {
        return new File(getProperty("java.io.tmpdir"));
    }
}
