package io.bitsmart.bdd.ft.report.ports.json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TestSuiteNameToFile {
    private final String name;
    private final String file;

    @JsonCreator
    public TestSuiteNameToFile(
        @JsonProperty("name") String name,
        @JsonProperty("file") String file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "TestSuiteNameToFile{" +
            "name='" + name + '\'' +
            ", file='" + file + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuiteNameToFile)) return false;
        TestSuiteNameToFile that = (TestSuiteNameToFile) o;
        return Objects.equals(name, that.name) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, file);
    }
}
