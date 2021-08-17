package io.bitsmart.bdd.report.report.filehandling;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Credit to the original source:
 *  https://github.com/eugenp/tutorials/blob/master/testing-modules/mocks/src/main/java/com/baeldung/jimfs/FileManipulation.java
 */
public class FileManipulation {

    void move(final Path origin, final Path destination) {
        try {
            Files.createDirectories(destination);
            Files.move(origin, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
