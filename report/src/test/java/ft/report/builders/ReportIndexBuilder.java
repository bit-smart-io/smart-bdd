package ft.report.builders;

import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;

public final class ReportIndexBuilder {
    private TestSuiteLinks links;
    private TestSuiteSummary summary;

    private ReportIndexBuilder() {
    }

    public static ReportIndexBuilder aReportIndex() {
        return new ReportIndexBuilder();
    }

    public ReportIndexBuilder withLinks(TestSuiteLinks links) {
        this.links = links;
        return this;
    }

    public ReportIndexBuilder withSummary(TestSuiteSummary summary) {
        this.summary = summary;
        return this;
    }

    public ReportIndex build() {
        return new ReportIndex(links, summary);
    }
}
