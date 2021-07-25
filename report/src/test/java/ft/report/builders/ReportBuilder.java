package ft.report.builders;

import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.BuilderUtils;

import java.util.ArrayList;
import java.util.List;

import static ft.report.builders.ReportIndexBuilder.*;

public final class ReportBuilder implements Builder<Report> {
    private ReportIndexBuilder reportIndex = aReportIndex();
    final private List<TestCaseBuilder> testCases = new ArrayList<>();
    final private List<TestSuiteBuilder> testSuites = new ArrayList<>();

    private ReportBuilder() {
    }

    public static ReportBuilder aReport() {
        return new ReportBuilder();
    }

    public ReportBuilder withReportIndex(ReportIndexBuilder reportIndex) {
        this.reportIndex = reportIndex;
        return this;
    }

    public ReportBuilder withTestCases(List<TestCaseBuilder> testCases) {
        this.testCases.clear();
        this.testCases.addAll(testCases);
        return this;
    }

    public ReportBuilder withTestSuites(List<TestSuiteBuilder> testSuites) {
        this.testSuites.clear();
        this.testSuites.addAll(testSuites);
        return this;
    }

    public Report build() {
        return new Report(reportIndex.build(), BuilderUtils.build(testCases), BuilderUtils.build(testSuites));
    }
}
