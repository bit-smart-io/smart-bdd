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

package io.bitsmart.bdd.ft;

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.bitsmart.bdd.ft.report.ports.utils.HtmlReportTestUtils.loadReportIndex;
import static io.bitsmart.bdd.ft.report.ports.utils.HtmlReportTestUtils.loadTestSuite;
import static org.assertj.core.api.Assertions.assertThat;

public class HtmlReportTest {

    private static String testSuite;

    @BeforeAll
    static void beforeAll() throws IOException {
        TestLauncher.launch(ClassUnderTest.class);
        testSuite = loadTestSuite(ClassUnderTest.class);
    }

    @Test
    void generatesIndexJson() throws IOException {
        String reportIndex = loadReportIndex();
        assertThat(reportIndex).isNotNull();
    }

    @Test
    void generatesTestSuiteJson() {
        assertThat(testSuite).isNotNull();
    }
}
