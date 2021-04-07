package report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import report.model.TestSuite;
import report.model.Report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.getProperty;

public class ReportWriter {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void write(Report report) {
//        report.getClassResultsList().forEach(this::sout);
        report.getTestSuites().forEach(this::write);
    }

    private void prepareDir() {
        File outputDir = outputDirectory();
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

    private void write(TestSuite testSuite) {
        try {
            String json = mapper.writeValueAsString(testSuite);
            File file = outputFile(fullyQualifiedName(testSuite));
            System.out.println("output: " + file);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fullyQualifiedName(TestSuite testSuite) {
        return testSuite.getName();
    }

    private static File outputFile(String testName) {
        return new File(outputDirectory(), "TEST-" + testName + "result.json");
    }

    private static File outputDirectory() {
        return new File(getProperty("java.io.tmpdir"));
    }
}
