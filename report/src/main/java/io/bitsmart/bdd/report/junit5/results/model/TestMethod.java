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

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Fields for the test suite method that are available at compile time: name, params, tags etc...
 *
 * For runtime time information please see TestSuiteMethodInvocation
 */
public class TestMethod {

    /** Name i.e. testMethod */
    private final String name;

//    /** Name i.e. testMethod(), testMethod(Method) etc... */
//    private final String nameWithParams;

    public TestMethod(String name) {
        this.name = name;
    }

    public static TestMethod testMethod(Method method) {
        if (method == null) {
            new TestMethod("error-could-not-get-method");
        }
        return new TestMethod(method.getName());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestMethod)) return false;
        TestMethod that = (TestMethod) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TestMethod{" +
            "name='" + name + '\'' +
            '}';
    }
}
