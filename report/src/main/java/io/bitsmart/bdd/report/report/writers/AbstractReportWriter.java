package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.report.filehandling.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public abstract class AbstractReportWriter {
    final FileRepository fileRepository = new FileRepository();
    final FileNameProvider fileNameProvider;

    public AbstractReportWriter(FileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    public void prepareDataDirectory() {
        final Path path = fileNameProvider.path();
        try {
            if (Files.exists(path)) {
                Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .forEach(this::deleteIfExists);
            }
            Files.createDirectories(path);
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

    void write(Path path, String contents) {
        if (!Files.exists(path)) {
            fileRepository.create(path);
        }
        fileRepository.update(path, contents);
        System.out.println("output: file://" + path);
    }
}
