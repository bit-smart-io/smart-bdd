package junit5.results.model;

public class TestSuiteResultsMetadata {
    private final int testCaseCount;
    private final int passedCount;
    private final int skippedCount;
//    private failures="0"
//    private errors="0"
//    private Datetime timestamp="2021-03-30T20:03:44"
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

    public TestSuiteResultsMetadata(int testCaseCount, int passedCount, int skippedCount) {
        this.testCaseCount = testCaseCount;
        this.passedCount = passedCount;
        this.skippedCount = skippedCount;
    }

    public int getTestCaseCount() {
        return testCaseCount;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }
}
