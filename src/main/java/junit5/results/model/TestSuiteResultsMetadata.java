package junit5.results.model;

public class TestSuiteResultsMetadata {
    private int testCaseCount;
//    private int skippedCount;
//    private failures="0"
//    private errors="0"
//    private Datetime timestamp="2021-03-30T20:03:44"
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

    public void incrementTest() {
        testCaseCount++;
    }

    public int getTestCaseCount() {
        return testCaseCount;
    }
}
