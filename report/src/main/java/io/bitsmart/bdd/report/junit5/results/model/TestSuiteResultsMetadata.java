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

package io.bitsmart.bdd.report.junit5.results.model;

import java.util.Objects;

public class TestSuiteResultsMetadata {
    //TODO count class?
    private final int testCaseCount;
    private final int passedCount;
    private final int skippedCount;
    private final int failedCount;
    private final int abortedCount;
//    private Datetime timestamp="2021-03-30T20:03:44"
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

    public TestSuiteResultsMetadata(int testCaseCount, int passedCount, int skippedCount, int failedCount, int abortedCount) {
        this.testCaseCount = testCaseCount;
        this.passedCount = passedCount;
        this.skippedCount = skippedCount;
        this.failedCount = failedCount;
        this.abortedCount = abortedCount;
    }

    public int getTestCaseCount() {
        return testCaseCount;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public int getAbortedCount() {
        return abortedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuiteResultsMetadata)) return false;
        TestSuiteResultsMetadata that = (TestSuiteResultsMetadata) o;
        return testCaseCount == that.testCaseCount && passedCount == that.passedCount && skippedCount == that.skippedCount && failedCount == that.failedCount && abortedCount == that.abortedCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testCaseCount, passedCount, skippedCount, failedCount, abortedCount);
    }

    @Override
    public String toString() {
        return "TestSuiteResultsMetadata{" +
            "testCaseCount=" + testCaseCount +
            ", passedCount=" + passedCount +
            ", skippedCount=" + skippedCount +
            ", failedCount=" + failedCount +
            ", abortedCount=" + abortedCount +
            '}';
    }
}
