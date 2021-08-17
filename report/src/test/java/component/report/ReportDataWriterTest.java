package component.report;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import component.report.builders.ReportBuilder;
import component.report.builders.ReportIndexBuilder;
import component.report.builders.TestCaseBuilder;
import component.report.builders.TestSuiteBuilder;
import component.report.builders.TestSuiteSummaryBuilder;
import io.bitsmart.bdd.report.report.FileNameProvider;
import io.bitsmart.bdd.report.report.ReportDataWriter;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestSuite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static component.report.builders.ReportIndexBuilder.aReportIndex;
import static component.report.builders.TestCaseBuilder.aTestCase;
import static component.report.builders.TestSuiteBuilder.aTestSuite;
import static component.report.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static component.report.builders.TestSuiteNameToFileBuilder.aTestSuiteNameToFile;
import static component.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportDataWriterTest {

    private final FileNameProvider fileNameProvider = mock(FileNameProvider.class);
    private final FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());
    private final ReportInMemoryTestUtils inMemoryTestUtils = new ReportInMemoryTestUtils(fileSystem);
    private final Path dataPath = fileSystem.getPath("/data");
    private final Path testSuitePath = dataPath.resolve("testSuite.json");
    private final Path indexPath = dataPath.resolve("index.json");

    private final ReportDataWriter reportDataWriter = new ReportDataWriter(fileNameProvider);
    private final Report report = aDefaultReport().build();

    @BeforeEach
    void setUp() {
        when(fileNameProvider.dataPath()).thenReturn(dataPath);
        when(fileNameProvider.outputFile(any(TestSuite.class))).thenReturn(testSuitePath);
        when(fileNameProvider.dataIndex()).thenReturn(indexPath);
    }

    @Test
    void prepareDataDirectory_deletesExistingDirectory() throws IOException {
        assertThat(dataPath).doesNotExist();
        Path testFile = dataPath.resolve("test.txt");

        Files.createDirectories(dataPath);
        Files.createFile(testFile);
        assertThat(testFile).exists();

        reportDataWriter.prepareDataDirectory();
        assertThat(testFile).doesNotExist();
        assertThat(dataPath).exists();
    }

    @Test
    void prepareDataDirectory_createsDirectory() throws IOException {
        assertThat(dataPath).doesNotExist();

        reportDataWriter.prepareDataDirectory();
        assertThat(dataPath).exists();
    }

    @Test
    void writesReportIndex() throws IOException {
        reportDataWriter.prepareDataDirectory();
        reportDataWriter.write(report);
        ReportIndex loadedReportIndex = inMemoryTestUtils.loadReportIndex(indexPath);
        assertThat(loadedReportIndex).isEqualTo(aDefaultReportIndex().build());
    }

    @Test
    void writesReportTestSuites() throws IOException {
        reportDataWriter.prepareDataDirectory();
        reportDataWriter.write(report);
        TestSuite testSuite = inMemoryTestUtils.loadTestSuite(testSuitePath);
        assertThat(testSuite).isEqualTo(aDefaultTestSuiteBuilder().build());
    }

    public static ReportBuilder aDefaultReport() {
        return ReportBuilder.aReport()
            .withReportIndex(aDefaultReportIndex())
            .withTestSuites(singletonList(aDefaultTestSuiteBuilder()))
            .withTestCases(singletonList(aDefaultTestCaseBuilder()));
    }

    public static TestSuiteBuilder aDefaultTestSuiteBuilder() {
        return aTestSuite()
            .withName("defaultName")
            .withClassName("defaultClassName")
            .withPackageName("defaultPackageName")
            .withMethodNames(singletonList("defaultMethodName"))
            .withTestCases(singletonList(aDefaultTestCase()))
            .withSummary(aDefaultSummary());
    }

    public static TestCaseBuilder aDefaultTestCaseBuilder() {
        return aTestCase()
            .withName("defaultTestCaseName")
            .withClassName("defaultClassName")
            .withPackageName("defaultPackageName")
            .withMethodName("defaultMethodName")
            .withWordify("defaultWordify")
            .withStatus(Status.PASSED);
    }

    public static TestSuiteSummaryBuilder aDefaultSummary() {
        return aTestSuiteSummary();
    }

    public static TestCaseBuilder aDefaultTestCase() {
        return aTestCase();
    }

    public static ReportIndexBuilder aDefaultReportIndex() {
        return aReportIndex()
            .withLinks(aTestSuiteLinks()
                .withTestSuites(singletonList(aTestSuiteNameToFile().withName("defaultName").withFile("defaultFileName"))))
            .withSummary(aTestSuiteSummary());
    }
}