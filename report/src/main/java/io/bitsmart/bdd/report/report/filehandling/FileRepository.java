package io.bitsmart.bdd.report.report.filehandling;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Credit to the original source:
 *  https://github.com/eugenp/tutorials/blob/master/testing-modules/mocks/src/main/java/com/baeldung/jimfs/FileRepository.java
 */
public class FileRepository {

    public void create(final Path path, final String fileName) {
        create(path.resolve(fileName));
    }

    public void create(final Path path) {
        try {
            Files.createFile(path);
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public String read(final Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public String update(final Path path, final String newContent) {
        try {
            Files.write(path, newContent.getBytes());
            return newContent;
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public void delete(final Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
