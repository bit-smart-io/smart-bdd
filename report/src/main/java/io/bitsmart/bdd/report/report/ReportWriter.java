package io.bitsmart.bdd.report.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.bitsmart.bdd.report.report.model.HomePage;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.Report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    TestSuiteFileUtil testSuiteFileUtil = new TestSuiteFileUtil();

    public void write(Report report) {
//        report.getClassResultsList().forEach(this::sout);
        write(report.getHomePage());
        report.getTestSuites().forEach(this::write);
    }

    private void prepareDir() {
        File outputDir = testSuiteFileUtil.outputDirectory();
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
            File file = testSuiteFileUtil.outputFileForReportIndex();
            System.out.println("output:  file://" + file);
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
            File file = testSuiteFileUtil.outputFile(testSuite);
            System.out.println("output: file://" + file);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
