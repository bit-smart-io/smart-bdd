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

import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class TestCaseNameFactoryTest {

    private final TestCaseNameFactory testCaseNameFactory = new TestCaseNameFactory();

    @Test
    void createName_withEmptyArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName");
    }

    @Test
    void createName_withOneArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(singletonList("arg 1")).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName arg 1");
    }

    @Test
    void createName_withTwoArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(asList("arg 1", "arg 2")).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName arg 1, arg 2");
    }

    @Test
    void createName_withTwoIntegerArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(asList(1, 2)).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName 1, 2");
    }

    @Test
    void createName_withOneNullArg() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(singletonList(null)).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName null");
    }

    private TestCaseResultBuilder aDefaultTestCaseResult() {
        return aTestCaseResult()
            .withMethod(new TestMethod("methodName"));
    }
}