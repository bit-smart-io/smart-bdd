package ft.report.builders;

import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.utils.Builder;

import static ft.report.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static ft.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;

public final class ReportIndexBuilder implements Builder<ReportIndex> {
    private TestSuiteLinksBuilder links = aTestSuiteLinks();
    private TestSuiteSummaryBuilder summary = aTestSuiteSummary();

    private ReportIndexBuilder() {
    }

    public static ReportIndexBuilder aReportIndex() {
        return new ReportIndexBuilder();
    }

    public ReportIndexBuilder withLinks(TestSuiteLinksBuilder links) {
        this.links = links;
        return this;
    }

    public ReportIndexBuilder withSummary(TestSuiteSummaryBuilder summary) {
        this.summary = summary;
        return this;
    }

    @Override
    public ReportIndex build() {
        return new ReportIndex(links.build(), summary.build());
    }
}
