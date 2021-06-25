package io.bitsmart.bdd.report.junit5.debugextension.utils.debugcapture.methods;

import org.junit.jupiter.api.extension.ExtensionContext;

public class BaseMethod {
    private final String name;
    private final ExtensionContext extensionContext;

    public BaseMethod(String name, ExtensionContext extensionContext) {
        this.name = name;
        this.extensionContext = extensionContext;
    }

    public String getName() {
        return name;
    }

    public ExtensionContext getExtensionContext() {
        return extensionContext;
    }

    public String getClassName() {
        return extensionContext.getRequiredTestClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "BaseMethod{" + verbose() + '}';
    }

    public String verbose() {
        return "name='" + name + '\'' +
            ", extensionContext=" + extensionContext +
            ", class=" + extensionContext.getRequiredTestClass().getSimpleName() +
            ", method=" + extensionContext.getTestMethod();
    }
}
