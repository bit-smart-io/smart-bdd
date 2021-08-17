package io.bitsmart.bdd.report.report.writers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.bitsmart.bdd.report.report.filehandling.FileRepository;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * TODO this is not unit tested.
 * <p>
 * https://www.baeldung.com/jimfs-file-system-mocking#:~:text=Jimfs%20is%20an%20in%2Dmemory,nio%20layer.
 * <p>
 * prepareDir() not working need to unit test!!!
 */
public class ReportDataWriter {
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final FileRepository fileRepository = new FileRepository();
    private final DataFileNameProvider fileNameProvider;

    public ReportDataWriter(DataFileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    public void write(Report report) {
        write(report.getIndex());
    }

    public void prepareDataDirectory() {
        final Path dataPath = fileNameProvider.dataPath();
        try {
            if (Files.exists(dataPath)) {
                Files.walk(dataPath)
                    .sorted(Comparator.reverseOrder())
                    .forEach(this::deleteIfExists);
            }
            Files.createDirectories(dataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean deleteIfExists(Path path) {
        try {
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void write(DataReportIndex dataReportIndex) {
        Path path = fileNameProvider.dataIndex();
        //TODO this is a workaround for running the this framework multiple times locally. This could be fixed.
        // normally you'd always create the file
        if (!Files.exists(path)) {
            fileRepository.create(path);
        }
        fileRepository.update(path, toJson(dataReportIndex));
        System.out.println("output: file://" + path);
    }

    public void write(TestSuite testSuite) {
        Path path = fileNameProvider.outputFile(testSuite);
        // see above
        if (!Files.exists(path)) {
            fileRepository.create(path);
        }
        fileRepository.update(path, toJson(testSuite));
        System.out.println("output: file://" + path);
    }

    private String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
