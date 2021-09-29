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

package io.bitsmart.bdd.ft.report.ports.json.builders;

import io.bitsmart.bdd.ft.report.ports.json.model.TestSuiteSummary;
import io.bitsmart.bdd.report.utils.Builder;

public final class TestSuiteSummaryBuilder implements Builder<TestSuiteSummary> {
    private int testCase;
    private int passed;
    private int skipped;
    private int failed;
    private int aborted;

    private TestSuiteSummaryBuilder() {
    }

    public static TestSuiteSummaryBuilder aTestSuiteSummary() {
        return new TestSuiteSummaryBuilder();
    }

    public TestSuiteSummaryBuilder withTestCase(int testCaseCount) {
        this.testCase = testCaseCount;
        return this;
    }

    public TestSuiteSummaryBuilder withPassed(int passedCount) {
        this.passed = passedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withSkipped(int skippedCount) {
        this.skipped = skippedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withFailed(int failedCount) {
        this.failed = failedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withAborted(int abortedCount) {
        this.aborted = abortedCount;
        return this;
    }

    public TestSuiteSummary build() {
        return new TestSuiteSummary(testCase, passed, skipped, failed, aborted);
    }
}
