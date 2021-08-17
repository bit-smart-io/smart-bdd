package component.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bitsmart.bdd.report.report.FileNameProvider;
import io.bitsmart.bdd.report.report.ReportDataWriter;
import io.bitsmart.bdd.report.report.filehandling.FileRepository;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import static java.lang.System.getProperty;

public class ReportInMemoryTestUtils {
    public static final String DATA_FOLDER = "io.bitsmart.bdd.report/data/";
    public static final String BASE_FOLDER = getProperty("java.io.tmpdir");

    private final FileSystem fileSystem;
    private final FileRepository fileRepository = new FileRepository();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public ReportInMemoryTestUtils(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public static void writeReport(Report report, FileNameProvider fileNameProvider) {
        ReportDataWriter reportDataWriter = new ReportDataWriter(fileNameProvider);
        reportDataWriter.write(report);
    }

    public TestSuite loadTestSuite(Class<?> clazz) throws IOException {
        String contents = fileRepository.read(testSuiteFile(clazz));
        return MAPPER.readValue(contents, TestSuite.class);
    }

    public TestSuite loadTestSuite(String clazz) throws IOException {
        String contents = fileRepository.read(testSuiteFile(clazz));
        return MAPPER.readValue(contents, TestSuite.class);
    }

    public TestSuite loadTestSuite(Path path) throws IOException {
        return MAPPER.readValue(fileRepository.read(path), TestSuite.class);
    }

    public ReportIndex loadReportIndex() throws IOException {
        String contents = fileRepository.read(homePageFile());
        return MAPPER.readValue(contents, ReportIndex.class);
    }

    public ReportIndex loadReportIndex(Path path) throws IOException {
        String contents = fileRepository.read(path);
        return MAPPER.readValue(contents, ReportIndex.class);
    }

    public Path homePageFile() {
        return dataPath().resolve("index.json");
    }

    public Path testSuiteFile(Class<?> clazz) {
        return dataPath().resolve("TEST-" + clazz.getName() + ".json");
    }

    public Path testSuiteFile(String clazz) {
        return dataPath().resolve("TEST-" + clazz + ".json");
    }

    public Path dataPath() {
        return fileSystem.getPath(BASE_FOLDER, DATA_FOLDER);
    }
}
