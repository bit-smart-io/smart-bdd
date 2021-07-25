package io.bitsmart.bdd.ft;

import io.bitsmart.bdd.ft.report.ports.json.model.ReportIndex;
import io.bitsmart.bdd.ft.report.ports.json.model.Status;
import io.bitsmart.bdd.ft.report.ports.json.model.TestSuite;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.bitsmart.bdd.ft.report.ports.json.builders.TestCaseBuilder.aTestCase;
import static io.bitsmart.bdd.ft.report.ports.json.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static io.bitsmart.bdd.ft.report.ports.utils.ReportTestUtils.loadReportIndex;
import static io.bitsmart.bdd.ft.report.ports.utils.ReportTestUtils.loadTestSuite;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Due to the static nature of ReportExtension, this class can't be annotated with ReportExtension
 * */
public class ReportTest {

    @Test
    void generatesIndexJson() throws IOException {
        TestLauncher.launch(ClassUnderTest.class);

        ReportIndex reportIndex = loadReportIndex();
        assertThat(reportIndex).isNotNull();
    }

    @Test
    void generatesClassUnderTestJson() throws IOException {
        TestLauncher.launch(ClassUnderTest.class);

        TestSuite testSuite = loadTestSuite(ClassUnderTest.class);
        assertThat(testSuite).isNotNull();
        assertThat(testSuite.getName()).isEqualTo("io.bitsmart.bdd.ft.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("io.bitsmart.bdd.ft");
        assertThat(testSuite.getMethodNames()).containsExactly("testMethod", "paramTest", "paramTest", "paramTest");
        assertThat(testSuite.getSummary()).isEqualTo(aTestSuiteSummary().withTestCase(4).withPassed(4).withSkipped(0).withFailed(0).withAborted(0).build());
    }

    @Test
    void verifyClassUnderTestJsonTopLevelFields() throws IOException {
        TestLauncher.launch(ClassUnderTest.class);

        TestSuite testSuite = loadTestSuite(ClassUnderTest.class);
        assertThat(testSuite.getName()).isEqualTo("io.bitsmart.bdd.ft.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("io.bitsmart.bdd.ft");
        assertThat(testSuite.getMethodNames()).containsExactly("testMethod", "paramTest", "paramTest", "paramTest");
        assertThat(testSuite.getSummary()).isEqualTo(aTestSuiteSummary().withTestCase(4).withPassed(4).withSkipped(0).withFailed(0).withAborted(0).build());
    }

    @Test
    void verifyClassUnderTestJsonFirstTestSuite() throws IOException {
        TestLauncher.launch(ClassUnderTest.class);

        TestSuite testSuite = loadTestSuite(ClassUnderTest.class);
        assertThat(testSuite.getTestCases()).contains(
            aTestCase()
                .withClassName("ClassUnderTest")
                .withPackageName("io.bitsmart.bdd.ft")
                .withMethodName("testMethod")
                .withName("testMethod")
                .withStatus(Status.PASSED)
                .withWordify("Passing assertion")
                .build());
    }
}
