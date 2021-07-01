package io.bitsmart.bdd.ft;

import io.bitsmart.bdd.ft.report.ports.json.model.ReportIndex;
import io.bitsmart.bdd.ft.report.ports.json.model.TestSuite;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    }
}
