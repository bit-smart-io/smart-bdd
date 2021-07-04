package ft.report.builders;

import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.utils.Builder;

public final class ReportIndexBuilder implements Builder<ReportIndex> {
    private TestSuiteLinksBuilder links;
    private TestSuiteSummaryBuilder summary;

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
