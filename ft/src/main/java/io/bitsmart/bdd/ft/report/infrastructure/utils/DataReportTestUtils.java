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

package io.bitsmart.bdd.ft.report.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.io.IOException;
import java.nio.file.Path;

public class DataReportTestUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static TestSuite loadTestSuite(Class<?> clazz) throws IOException {
        String contents = new FileLoader().read(testSuiteFile(clazz));
        return MAPPER.readValue(contents, TestSuite.class);
    }

    public static DataReportIndex loadReportIndex() throws IOException {
        String contents = new FileLoader().read(homePageFile());
        return MAPPER.readValue(contents, DataReportIndex.class);
    }

    public static Path homePageFile() {
        return outputDirectory().resolve("index.json");
    }

    public static Path testSuiteFile(Class<?> clazz) {
        return outputDirectory().resolve("TEST-" + clazz.getCanonicalName() + ".json");
    }

    public static Path outputDirectory() {
        return TestConfig.getBasePath().resolve("io.bitsmart.bdd.report/data/");
    }
}
