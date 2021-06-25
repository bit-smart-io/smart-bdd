package io.bitsmart.bdd.report.junit5.debugextension.utils.debugcapture.methods;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;

public class DynamicTest extends BaseMethod {
    private final InvocationInterceptor.Invocation<Void> invocation;

    public DynamicTest(
        String name,
        InvocationInterceptor.Invocation<Void> invocation,
        ExtensionContext extensionContext) {
        super(name, extensionContext);
        this.invocation = invocation;
    }

    public InvocationInterceptor.Invocation<Void> getInvocation() {
        return invocation;
    }

    @Override
    public String toString() {
        return verbose();
    }
}
