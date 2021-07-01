package ft.report.builders;

import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;

import java.util.List;

public final class TestSuiteLinksBuilder {
    private List<TestSuiteNameToFile> testSuites;

    private TestSuiteLinksBuilder() {
    }

    public static TestSuiteLinksBuilder aTestSuiteLinks() {
        return new TestSuiteLinksBuilder();
    }

    public TestSuiteLinksBuilder withTestSuites(List<TestSuiteNameToFile> testSuites) {
        this.testSuites = testSuites;
        return this;
    }

    public TestSuiteLinks build() {
        return new TestSuiteLinks(testSuites);
    }
}
