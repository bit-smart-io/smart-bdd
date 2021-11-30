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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.bitsmart.bdd.ft.report.ports.json.model.notes.Notes;

import java.util.Objects;

/**
 * Name ideas: ReportItem, Item, ResultItem
 * java.lang.reflect.Parameter
 * java.lang.reflect.Method#getParameterTypes()
 */
public class TestCase {
    private final String wordify;
    private final Status status;
    private final String name;
    private final String methodName;
    private final String methodNameWordified;
    private final String className;
    private final String packageName;
    private final Notes notes;
    //private final List<String> parameters;
    // time started
    // time taken

    @JsonCreator
    public TestCase(
        @JsonProperty("wordify") String wordify,
        @JsonProperty("status") Status status,
        @JsonProperty("name") String name,
        @JsonProperty("methodName") String methodName,
        @JsonProperty("methodNameWordified") String methodNameWordified,
        @JsonProperty("className") String className,
        @JsonProperty("packageName") String packageName,
        @JsonProperty("notes") Notes notes)
    {
        this.wordify = wordify;
        this.status = status;
        this.name = name;
        this.methodName = methodName;
        this.methodNameWordified = methodNameWordified;
        this.className = className;
        this.packageName = packageName;
        this.notes = notes;
    }

    public String getWordify() {
        return wordify;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodNameWordified() {
        return methodNameWordified;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public Notes getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCase)) return false;
        TestCase testCase = (TestCase) o;
        return Objects.equals(wordify, testCase.wordify) && status == testCase.status && Objects.equals(name, testCase.name) && Objects.equals(methodName, testCase.methodName) && Objects.equals(methodNameWordified, testCase.methodNameWordified) && Objects.equals(className, testCase.className) && Objects.equals(packageName, testCase.packageName) && Objects.equals(notes, testCase.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, name, methodName, methodNameWordified, className, packageName, notes);
    }

    @Override
    public String toString() {
        return "TestCase{" +
            "wordify='" + wordify + '\'' +
            ", status=" + status +
            ", name='" + name + '\'' +
            ", methodName='" + methodName + '\'' +
            ", methodNameWordified='" + methodNameWordified + '\'' +
            ", className='" + className + '\'' +
            ", packageName='" + packageName + '\'' +
            ", notes=" + notes +
            '}';
    }
}
