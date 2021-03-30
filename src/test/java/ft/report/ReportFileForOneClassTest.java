package ft.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.ReportWriter;
import report.ResultsExtension;
import report.model.ClassResults;
import report.model.Report;
import report.model.Status;
import report.model.TestResult;

import java.io.File;
import java.io.IOException;

import static ft.report.ResultBuilder.aResult;
import static java.lang.System.getProperty;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportFileForOneClassTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
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
