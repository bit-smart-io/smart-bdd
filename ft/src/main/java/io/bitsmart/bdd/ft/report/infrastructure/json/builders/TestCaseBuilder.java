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

package io.bitsmart.bdd.ft.report.infrastructure.json.builders;

import io.bitsmart.bdd.ft.report.infrastructure.json.model.Status;
import io.bitsmart.bdd.ft.report.infrastructure.json.model.TestCase;
import io.bitsmart.bdd.ft.report.infrastructure.json.model.notes.Notes;
import io.bitsmart.bdd.report.utils.Builder;

public final class TestCaseBuilder implements Builder<TestCase> {
    private String wordify;
    private Status status;
    private String methodName;
    private String methodNamWordified;
    private String className;
    private String packageName;
    private Notes notes;

    private TestCaseBuilder() {
    }

    public static TestCaseBuilder aTestCase() {
        return new TestCaseBuilder();
    }

    public TestCaseBuilder withWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public TestCaseBuilder withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public TestCaseBuilder withMethodNameWordified(String methodNameWordified) {
        this.methodNamWordified = methodNameWordified;
        return this;
    }

    public TestCaseBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    public TestCaseBuilder withPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public TestCaseBuilder withNotes(Notes notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public TestCase build() {
        return new TestCase(wordify, status, methodName, methodNamWordified, className, packageName, notes);
    }
}
