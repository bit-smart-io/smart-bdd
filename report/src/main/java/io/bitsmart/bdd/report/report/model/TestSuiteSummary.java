package io.bitsmart.bdd.report.report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TestSuiteSummary {
    private final int tests;
    private final int passed;
    private final int skipped;
    private final int failed;
    private final int aborted;
//    private Datetime timestamp="2021-03-30T20:03:44
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

    @JsonCreator
    public TestSuiteSummary(
        @JsonProperty("count") int tests,
        @JsonProperty("passed") int passed,
        @JsonProperty("skipped") int skipped,
        @JsonProperty("failed") int failed,
        @JsonProperty("aborted") int aborted)
    {
        this.tests = tests;
        this.passed = passed;
        this.skipped = skipped;
        this.failed = failed;
        this.aborted = aborted;
    }

    public int getTests() {
        return tests;
    }

    public int getPassed() {
        return passed;
    }

    public int getSkipped() {
        return skipped;
    }

    public int getFailed() {
        return failed;
    }

    public int getAborted() {
        return aborted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuiteSummary)) return false;
        TestSuiteSummary that = (TestSuiteSummary) o;
        return tests == that.tests && passed == that.passed && skipped == that.skipped && failed == that.failed && aborted == that.aborted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tests, passed, skipped, failed, aborted);
    }

    @Override
    public String toString() {
        return "TestSuiteSummary{" +
            "tests=" + tests +
            ", passed=" + passed +
            ", skipped=" + skipped +
            ", failed=" + failed +
            ", aborted=" + aborted +
            '}';
    }
}
