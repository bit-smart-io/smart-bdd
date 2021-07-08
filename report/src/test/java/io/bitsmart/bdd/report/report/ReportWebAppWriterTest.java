package io.bitsmart.bdd.report.report;

import ft.report.ReportTestUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

//TODO this concept doesn't work
class ReportWebAppWriterTest {

    @Test
    void copiesAndPastesTheWebAppToTheTempDir() throws Exception {
        // in here must a folder to
        File outputDirectory = ReportTestUtils.outputDirectory();
        System.out.println("output dir: " + outputDirectory);
        System.out.println("output dir exists: " + outputDirectory.exists());

        if (!outputDirectory.exists()) {
            boolean bool = outputDirectory.mkdir();
            if (bool) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Sorry couldn’t create specified directory");
            }
        }

        // for debugging
        allFilesFor(outputDirectory.toPath()).forEach(System.out::println);

        // list the files
        // copy some files across

        // now clear and make sure the dir is cleared.

        // inject the temp directory
        ReportWebAppWriter reportWebAppWriter = new ReportWebAppWriter();
        reportWebAppWriter.prepare();

        System.out.println("index: " + "file:///" + ReportTestUtils.outputDirectory() + "/index.html");
    }

    public List<File> allFilesFor(Path path) throws URISyntaxException, IOException {
        return Files.walk(path)
            .filter(Files::isRegularFile)
            .map(Path::toFile)
            .collect(Collectors.toList());
    }

}