package io.bitsmart.bdd.report.junit5.results;

import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;

import java.util.List;

/**
 * private Datetime timestamp="2021-03-30T20:03:44"
 * private String hostname="Jamess-MacBook-Pro.local"
 * privat long time="0.021"
 */
public class ReportSummaryFactory {
    public static TestSuiteSummary create(List<TestSuite> testSuites) {
        ReportSummaryBuilder builder = new ReportSummaryBuilder();
        testSuites.forEach(testSuite -> builder.increment(testSuite.getSummary()));
        return builder.build();
    }

    static class ReportSummaryBuilder {
        private int testCount;
        private int passedCount;
        private int skippedCount;
        private int failedCount;
        private int abortedCount;

        public void increment(TestSuiteSummary summary) {
            testCount += summary.getTests();
            passedCount += summary.getPassed();
            skippedCount += summary.getSkipped();
            failedCount += summary.getFailed();
            abortedCount += summary.getAborted();
        }

        private TestSuiteSummary build() {
            return new TestSuiteSummary(
                testCount,
                passedCount,
                skippedCount,
                failedCount,
                abortedCount);
        }
    }
}
