package component.report.builders;

import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.utils.Builder;

import static component.report.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static component.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;

public final class ReportIndexBuilder implements Builder<DataReportIndex> {
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
    public DataReportIndex build() {
        return new DataReportIndex(links.build(), summary.build());
    }
}
