package component.report.builders;

import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestCase;
import io.bitsmart.bdd.report.report.model.notes.Notes;
import io.bitsmart.bdd.report.utils.Builder;

public final class TestCaseBuilder implements Builder<TestCase> {
    private String wordify;
    private Status status;
    private String methodName;
    private String name;
    private String className;
    private String packageName;
    private Notes notes;

    private TestCaseBuilder() {
    }

    public static TestCaseBuilder aTestCase() {
        return new TestCaseBuilder();
    }

    public TestCaseBuilder withWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public TestCaseBuilder withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public TestCaseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestCaseBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    public TestCaseBuilder withPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public TestCaseBuilder withNotes(Notes notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public TestCase build() {
        return new TestCase(wordify, status, name, methodName, className, packageName, notes);
    }
}
