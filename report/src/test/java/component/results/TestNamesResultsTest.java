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

package component.results;

import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shared.undertest.TestNamesTest;

import static org.assertj.core.api.Assertions.assertThat;

public class TestNamesResultsTest extends AbstractResultsForTestSuite {

    @Override
    public Class<?> classUnderTest() {
        return TestNamesTest.class;
    }

    @Test
    void verifyFirstTest_noParams() {
        TestCaseResult testCaseResult = testCaseResult(0);
        assertThat(testCaseResult.getName()).isEqualTo("testMethod");
        assertThat(testCaseResult.getMethod()).isEqualTo(method("testMethod"));
    }

    @Test
    void verifySecondTest_paramsNoCustomName() {
        assertThat(testCaseResult(1).getName()).isEqualTo("paramTest value 1");
        assertThat(testCaseResult(1).getMethod()).isEqualTo(method("paramTest"));

        assertThat(testCaseResult(2).getName()).isEqualTo("paramTest value 2");
        assertThat(testCaseResult(2).getMethod()).isEqualTo(method("paramTest"));

        assertThat(testCaseResult(3).getName()).isEqualTo("paramTest value 3");
        assertThat(testCaseResult(3).getMethod()).isEqualTo(method("paramTest"));
    }

    @Disabled
    @Test
    void verifyThirdTest_paramsWithCustomName() {
        assertThat(testCaseResult(4).getName()).isEqualTo("#0 - value = 1");
        assertThat(testCaseResult(4).getMethod()).isEqualTo(method("paramTestWithCustomName"));

        assertThat(testCaseResult(5).getName()).isEqualTo("#0 - value = 2");
        assertThat(testCaseResult(5).getMethod()).isEqualTo(method("paramTestWithCustomName"));

        assertThat(testCaseResult(6).getName()).isEqualTo("#0 - value = 3");
        assertThat(testCaseResult(6).getMethod()).isEqualTo(method("paramTestWithCustomName"));
    }
}
