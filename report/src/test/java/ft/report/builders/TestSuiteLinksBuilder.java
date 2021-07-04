package ft.report.builders;

import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.utils.Builder;

import java.util.List;

public final class TestSuiteLinksBuilder implements Builder<TestSuiteLinks> {
    private List<TestSuiteNameToFileBuilder> testSuites;

    private TestSuiteLinksBuilder() {
    }

    public static TestSuiteLinksBuilder aTestSuiteLinks() {
        return new TestSuiteLinksBuilder();
    }

    public TestSuiteLinksBuilder withTestSuites(List<TestSuiteNameToFileBuilder> testSuites) {
        this.testSuites = testSuites;
        return this;
    }

    @Override
    public TestSuiteLinks build() {
        return new TestSuiteLinks(BuilderUtils.build((testSuites)));
    }
}
