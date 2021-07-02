package io.bitsmart.bdd.report.report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportIndex {
    private final TestSuiteLinks links;
    private final TestSuiteSummary summary;

    @JsonCreator
    public ReportIndex(
        @JsonProperty("links") TestSuiteLinks links,
        @JsonProperty("summary") TestSuiteSummary summary) {
        this.links = links;
        this.summary = summary;
    }

    public TestSuiteLinks getLinks() {
        return links;
    }

    public TestSuiteSummary getSummary() {
        return summary;
    }
}
