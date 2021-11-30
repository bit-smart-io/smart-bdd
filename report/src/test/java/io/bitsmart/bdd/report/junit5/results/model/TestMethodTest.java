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

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class TestMethodTest {

    @Test
    void createsTestMethod() {
        TestMethodTest testMethodTest = new TestMethodTest();
        Class<? extends TestMethodTest> clazz = testMethodTest.getClass();
        Method javaMethod = method(clazz, "myTestMethod");

        TestMethod testMethod = TestMethod.testMethod(javaMethod);

        assertThat(testMethod.getName()).isEqualTo("myTestMethod");
        assertThat(testMethod.getWordify()).isEqualTo("My test method");
    }

    private Method method(Class<? extends TestMethodTest> clazz, String methodName) {
        try {
            return clazz.getMethod(methodName);
        } catch(NoSuchMethodException e) {
            System.out.println(e);
        }
        return null;
    }

    public void myTestMethod() {}
}