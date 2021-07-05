package io.bitsmart.bdd.report.report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TestSuiteLinks {
    private final List<TestSuiteNameToFile> testSuites;

    @JsonCreator
    public TestSuiteLinks(@JsonProperty("testSuites") List<TestSuiteNameToFile> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TestSuiteNameToFile> getTestSuites() {
        return testSuites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuiteLinks)) return false;
        TestSuiteLinks that = (TestSuiteLinks) o;
        return Objects.equals(testSuites, that.testSuites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testSuites);
    }

    @Override
    public String toString() {
        return "TestSuiteLinks{" +
            "testSuites=" + testSuites +
            '}';
    }
}
