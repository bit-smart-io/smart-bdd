package ft.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.ReportWriter;
import report.ResultsExtension;
import report.model.ClassResults;
import report.model.TestResult;
import report.model.Report;
import report.model.Status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.getProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static ft.report.ResultBuilder.aResult;

public class ReportLauncherTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void createReport() {
        TestLauncher.launch(CLASS_UNDER_TEST);

        Report report = ReportFactory.create(ResultsExtension.getTestResultsForClasses());
        assertThat(report).isNotNull();
        assertThat(report.getTestResults()).hasSize(5);

        assertThat(report.getTestResults()).contains(firstTestResult());
    }

    @Test
    void writeReport() throws IOException {
        TestLauncher.launch(CLASS_UNDER_TEST);

        Report report = ReportFactory.create(ResultsExtension.getTestResultsForClasses());

        assertThat(report.getTestResults()).contains(firstTestResult());
        ReportWriter reportWriter = new ReportWriter();
        reportWriter.write(report);

        String contents = new FileLoader().toString(outputFile("testName"));
        ObjectMapper mapper = new ObjectMapper();
        ClassResults classResults = mapper.readValue(contents, ClassResults.class);

        assertThat(classResults.getTestResults()).contains(firstTestResult());
    }

    private static File outputFile(String testName) {
        return new File(outputDirectory(), "TEST-" + testName + "result.json");
    }

    private static File outputDirectory() {
        return new File(getProperty("java.io.tmpdir"));
    }

    @Test
    public void whenWriteToTmpFile_thenCorrect() throws IOException {
        String toWrite = "Hello";
        File tmpFile = File.createTempFile("test", ".tmp");
        System.out.println("tmpFile: " + tmpFile);
        FileWriter writer = new FileWriter(tmpFile);
        writer.write(toWrite);
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader(tmpFile));
        assertThat(toWrite).isEqualTo(reader.readLine());
        reader.close();
    }

    private TestResult firstTestResult() {
        return aResult()
            .withWordify("Assert that \"first test\" is equal to \"first test\"")
            .withStatus(Status.PASSED)
            .withMethodName("firstTest")
            .withClassName("ClassUnderTest")
            .withPackageName("ft.report")
            .build();
    }
}
