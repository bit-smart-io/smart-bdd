package component.report.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bitsmart.bdd.report.report.filehandling.FileRepository;
import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.io.IOException;
import java.nio.file.Path;

public class ReportLoadFileUtils {
    private final FileRepository fileRepository = new FileRepository();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public TestSuite loadTestSuite(Path path) throws IOException {
        return MAPPER.readValue(fileRepository.read(path), TestSuite.class);
    }

    public DataReportIndex loadReportIndex(Path path) throws IOException {
        String contents = fileRepository.read(path);
        return MAPPER.readValue(contents, DataReportIndex.class);
    }
}

