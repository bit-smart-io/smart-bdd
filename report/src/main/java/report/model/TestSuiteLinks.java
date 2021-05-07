package report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestSuiteLinks {
    private final List<TestSuiteNameToFile> testSuites;

    @JsonCreator
    public TestSuiteLinks(@JsonProperty("testSuites") List<TestSuiteNameToFile> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TestSuiteNameToFile> getTestSuites() {
        return testSuites;
    }
}
