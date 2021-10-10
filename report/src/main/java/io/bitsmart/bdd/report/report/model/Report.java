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

package io.bitsmart.bdd.report.report.model;

import java.util.List;
import java.util.Objects;

public class Report {
    // ReportIndex is for data. Maybe need one for html?
    private final DataReportIndex dataReportIndex;
    private final List<TestCase> testCases;
    private final List<TestSuite> testSuites;

    public Report(DataReportIndex dataReportIndex, List<TestCase> testCases, List<TestSuite> testSuites) {
        this.dataReportIndex = dataReportIndex;
        this.testCases = testCases;
        this.testSuites = testSuites;
    }

    public DataReportIndex getIndex() {
        return dataReportIndex;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    @Override
    public String toString() {
        return "Report{" +
            "reportIndex=" + dataReportIndex +
            ", testCases=" + testCases +
            ", testSuites=" + testSuites +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(dataReportIndex, report.dataReportIndex) && Objects.equals(testCases, report.testCases) && Objects.equals(testSuites, report.testSuites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataReportIndex, testCases, testSuites);
    }
}
