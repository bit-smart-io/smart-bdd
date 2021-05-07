package report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import report.model.HomePage;
import report.model.TestSuite;
import report.model.Report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.getProperty;

public class ReportWriter {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    TestSuiteFileService testSuiteFileService = new TestSuiteFileService();

    public void write(Report report) {
//        report.getClassResultsList().forEach(this::sout);
        write(report.getHomePage());
        report.getTestSuites().forEach(this::write);
    }

    private void prepareDir() {
        File outputDir = testSuiteFileService.outputDirectory();
        outputDir.delete();
        outputDir.getParentFile().mkdirs();
    }

    private void sout(TestSuite testSuite) {
        try {
            String json = mapper.writeValueAsString(testSuite);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(HomePage homePage) {
        try {
            String json = mapper.writeValueAsString(homePage);
            File file = testSuiteFileService.outputFileForReportIndex();
            System.out.println("output: " + file);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(TestSuite testSuite) {
        try {
            String json = mapper.writeValueAsString(testSuite);
            File file = testSuiteFileService.outputFile(testSuite);
            System.out.println("output: " + file);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
