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

public final class TestSuiteResultsMetadataBuilder {
    private int testCaseCount = 0;
    private int passedCount = 0;
    private int skippedCount = 0;
    private int failedCount = 0;
    private int abortedCount = 0;

    private TestSuiteResultsMetadataBuilder() {
    }

    public static TestSuiteResultsMetadataBuilder aTestSuiteResultsMetadata() {
        return new TestSuiteResultsMetadataBuilder();
    }

    public TestSuiteResultsMetadataBuilder withTestCaseCount(int testCaseCount) {
        this.testCaseCount = testCaseCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withPassedCount(int passedCount) {
        this.passedCount = passedCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withFailedCount(int failedCount) {
        this.failedCount = failedCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withAbortedCount(int abortedCount) {
        this.abortedCount = abortedCount;
        return this;
    }

    public TestSuiteResultsMetadata build() {
        return new TestSuiteResultsMetadata(testCaseCount, passedCount, skippedCount, failedCount, abortedCount);
    }
}
