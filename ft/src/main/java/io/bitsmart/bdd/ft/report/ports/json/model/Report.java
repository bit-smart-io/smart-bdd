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

package io.bitsmart.bdd.ft.report.ports.json.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Note: this is a domain object holding view objects. This is okay for now.
 *
 *  TODO reportBuilder or are we okay updating this?
 */
public class Report {
    private ReportIndex reportIndex;
    private List<TestCase> testCases = new ArrayList<>();
    private List<TestSuite> testSuites = new ArrayList<>();

    public ReportIndex getHomePage() {
        return reportIndex;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setReportIndex(ReportIndex reportIndex) {
        this.reportIndex = reportIndex;
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public void addTestSuite(TestSuite testSuite) {
       this.testSuites.add(testSuite);
    }

    @Override
    public String toString() {
        return "Report{" +
            "reportIndex=" + reportIndex +
            ", testCases=" + testCases +
            ", testSuites=" + testSuites +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(reportIndex, report.reportIndex) && Objects.equals(testCases, report.testCases) && Objects.equals(testSuites, report.testSuites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportIndex, testCases, testSuites);
    }
}
