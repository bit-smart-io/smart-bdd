package ft.report.builders;

import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.utils.Builder;

public final class TestSuiteNameToFileBuilder implements Builder<TestSuiteNameToFile>  {
    private String name;
    private String file;

    private TestSuiteNameToFileBuilder() {
    }

    public static TestSuiteNameToFileBuilder aTestSuiteNameToFile() {
        return new TestSuiteNameToFileBuilder();
    }

    public TestSuiteNameToFileBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestSuiteNameToFileBuilder withFile(String file) {
        this.file = file;
        return this;
    }

    public TestSuiteNameToFile build() {
        return new TestSuiteNameToFile(name, file);
    }
}
