package io.bitsmart.bdd.report.report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportIndex)) return false;
        ReportIndex that = (ReportIndex) o;
        return Objects.equals(links, that.links) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(links, summary);
    }

    @Override
    public String toString() {
        return "ReportIndex{" +
            "links=" + links +
            ", summary=" + summary +
            '}';
    }
}