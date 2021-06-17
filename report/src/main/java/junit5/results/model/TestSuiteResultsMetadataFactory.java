package junit5.results.model;

import java.util.List;

class TestSuiteResultsMetadataFactory {
    public static TestSuiteResultsMetadata create(List<TestCaseResult> testCaseResults) {
        TestSuiteResultsMetadataBuilder builder = new TestSuiteResultsMetadataBuilder();
        testCaseResults.forEach(testSuite -> builder.increment(testSuite.getStatus()));
        return builder.build();
    }

    static class TestSuiteResultsMetadataBuilder {
        private int testCount;
        private int passedCount;
        private int skippedCount;
        private int failedCount;
        private int abortedCount;
//    private Datetime timestamp="2021-03-30T20:03:44"
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

        public void increment(TestCaseResultStatus status) {
            testCount++;
            switch (status) {
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
        }

        private TestSuiteResultsMetadata build() {
            return new TestSuiteResultsMetadata(
                testCount,
                passedCount,
                skippedCount,
                failedCount,
                abortedCount);
        }
    }
}
