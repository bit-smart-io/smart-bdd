/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2022  James Bayliss
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

package io.bitsmart.bdd.ft.common;

import io.bitsmart.bdd.ft.undertest.basic.AbortedTestCasesUnderTest;
import io.bitsmart.bdd.ft.undertest.basic.ClassUnderTest;
import io.bitsmart.bdd.ft.undertest.basic.DisabledTestCasesUnderTest;
import io.bitsmart.bdd.ft.undertest.basic.FailedDueToExceptionTestCasesUnderTest;
import io.bitsmart.bdd.ft.undertest.basic.FailedTestCasesUnderTest;
import io.bitsmart.bdd.ft.undertest.basic.OutputStreamClassUnderTest;
import io.bitsmart.bdd.ft.undertest.basic.TestNamesTest;
import io.bitsmart.bdd.ft.undertest.scenarios.bookstore.BaseBookStoreUnderTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractReportTest {
    //static final String DEFAULT_DATE_TIME = "2000-01-29T09:15:30.00Z";
    //static final Clock CLOCK = Clock.fixed(Instant.parse(DEFAULT_DATE_TIME), ZoneId.of("UTC"));

    @BeforeAll
    public static void enableTest() {
        AbortedTestCasesUnderTest.setEnabled(true);
        FailedTestCasesUnderTest.setEnabled(true);
        BaseBookStoreUnderTest.setEnabled(true);

        AbortedTestCasesUnderTest.setEnabled(true);
        ClassUnderTest.setEnabled(true);
        DisabledTestCasesUnderTest.setEnabled(true);
        FailedDueToExceptionTestCasesUnderTest.setEnabled(true);
        FailedTestCasesUnderTest.setEnabled(true);
        OutputStreamClassUnderTest.setEnabled(true);
        TestNamesTest.setEnabled(true);
    }

    @AfterAll
    public static void disableTest() {
        AbortedTestCasesUnderTest.setEnabled(false);
        FailedTestCasesUnderTest.setEnabled(false);
        BaseBookStoreUnderTest.setEnabled(false);

        AbortedTestCasesUnderTest.setEnabled(false);
        ClassUnderTest.setEnabled(false);
        DisabledTestCasesUnderTest.setEnabled(false);
        FailedDueToExceptionTestCasesUnderTest.setEnabled(false);
        FailedTestCasesUnderTest.setEnabled(false);
        OutputStreamClassUnderTest.setEnabled(false);
        TestNamesTest.setEnabled(false);
    }
}
