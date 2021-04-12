package junit5.results.model;

import java.util.List;

class TestSuiteResultsMetadataFactory {
    private int testCaseCount;
    private int passedCount;
    private int skippedCount;
    private int failedCount;
    private int abortedCount;
//    private Datetime timestamp="2021-03-30T20:03:44"
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

    public TestSuiteResultsMetadata testSuiteResultsMetadata(List<TestCaseResult> testCaseResults) {
        testCaseResults.forEach(testCaseResult -> {
            testCaseCount++;
            switch (testCaseResult.getStatus()) {
                case PASSED:
                    passedCount++;
                    break;
                case DISABLED:
                    skippedCount++;
                    break;
                case FAILED:
                    failedCount++;
                    break;
                case ABORTED:
                    abortedCount++;
                    break;
            }
        });

        return new TestSuiteResultsMetadata(testCaseCount, passedCount, skippedCount, failedCount, abortedCount);
    }
}
