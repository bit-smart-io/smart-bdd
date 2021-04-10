package ft.report.oneclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import ft.report.FileLoader;
import junit5.results.ReportFactory;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.ReportWriter;
import junit5.results.ResultsExtension;
import report.model.TestSuite;
import report.model.Report;
import report.model.Status;
import report.model.TestCase;

import java.io.File;
import java.io.IOException;

import static ft.report.ResultBuilder.aResult;
import static java.lang.System.getProperty;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.learning.parameters.LearningTest" tests="8" skipped="0" failures="0" errors="0" timestamp="2021-03-30T20:03:44" hostname="Jamess-MacBook-Pro.local" time="0.021">
 *   <properties/>
 *   <testcase name="[1] test, TEST" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="[2] tEst, TEST" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="[3] Java, JAVA" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="RepeatingTest 1/5" classname="junit5.learning.parameters.LearningTest" time="0.005"/>
 *   <testcase name="RepeatingTest 2/5" classname="junit5.learning.parameters.LearningTest" time="0.0"/>
 *   <testcase name="RepeatingTest 3/5" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="RepeatingTest 4/5" classname="junit5.learning.parameters.LearningTest" time="0.0"/>
 *   <testcase name="RepeatingTest 5/5" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <system-out><![CDATA[RepeatingTest 1/5-->1
 * RepeatingTest 2/5-->2
 * RepeatingTest 3/5-->3
 * RepeatingTest 4/5-->4
 * RepeatingTest 5/5-->5
 * ]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
public class ReportFileForOneClassTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void writeReport() throws IOException {
        TestLauncher.launch(CLASS_UNDER_TEST);

        Report report = ReportFactory.create(ResultsExtension.getAllResults());

        assertThat(report.getTestCases()).contains(firstTestResult());
        ReportWriter reportWriter = new ReportWriter();
        reportWriter.write(report);

        String contents = new FileLoader().toString(outputFile(ClassUnderTest.class.getName()));
        ObjectMapper mapper = new ObjectMapper();
        TestSuite testSuite = mapper.readValue(contents, TestSuite.class);

        assertThat(testSuite.getTestResults()).contains(firstTestResult());
    }

    private static File outputFile(String testName) {
        return new File(outputDirectory(), "TEST-" + testName + "result.json");
    }

    private static File outputDirectory() {
        return new File(getProperty("java.io.tmpdir"));
    }

    private TestCase firstTestResult() {
        return aResult()
            .withWordify("Assert that \"first test\" is equal to \"first test\"")
            .withStatus(Status.PASSED)
            .withMethodName("firstTest")
            .withClassName("ClassUnderTest")
            .withPackageName("ft.report.oneclass")
            .build();
    }

//    private TestCase testResult(String method, String wordify) {
//        return aResult()
//            .withWordify(wordify)
//            .withStatus(Status.PASSED)
//            .withMethodName(method)
//            .withClassName("ClassUnderTest")
//            .withPackageName("ft.report.oneclass")
//            .build();
//    }
}
