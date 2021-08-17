package io.bitsmart.bdd.report.report.filehandling;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

/**
 * Credit to the original source:
 *  https://github.com/eugenp/tutorials/blob/master/testing-modules/mocks/src/main/java/com/baeldung/jimfs/FilePathReader.java
 */
class FilePathReader {

    String getSystemPath(final Path path) {
        try {
            return path
              .toRealPath()
              .toString();
        } catch (final IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
