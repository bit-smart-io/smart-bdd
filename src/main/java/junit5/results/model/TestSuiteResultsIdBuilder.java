package junit5.results.model;

//TODO move to test
public final class TestSuiteResultsIdBuilder {
    private String name;
    private String className;
    private String packageName;

    private TestSuiteResultsIdBuilder() {
    }

    public static TestSuiteResultsIdBuilder aTestSuiteResultsId() {
        return new TestSuiteResultsIdBuilder();
    }

    public TestSuiteResultsIdBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestSuiteResultsIdBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    public TestSuiteResultsIdBuilder withPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public TestSuiteResultsId build() {
        return new TestSuiteResultsId(name, className, packageName);
    }
}
