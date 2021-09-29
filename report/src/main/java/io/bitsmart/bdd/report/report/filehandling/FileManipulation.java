/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
