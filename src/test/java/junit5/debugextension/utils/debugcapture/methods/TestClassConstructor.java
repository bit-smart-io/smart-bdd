package junit5.debugextension.utils.debugcapture.methods;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Constructor;

public class TestClassConstructor<T> extends BaseMethod {
    private final InvocationInterceptor.Invocation<T> invocation;
    private final ReflectiveInvocationContext<Constructor<T>> invocationContext;

    public TestClassConstructor(
        String name,
        InvocationInterceptor.Invocation<T> invocation,
        ReflectiveInvocationContext<Constructor<T>> invocationContext,
        ExtensionContext extensionContext
    ) {
        super(name, extensionContext);
        this.invocation = invocation;
        this.invocationContext = invocationContext;
    }

    public InvocationInterceptor.Invocation<T> getInvocation() {
        return invocation;
    }

    public ReflectiveInvocationContext<Constructor<T>> getInvocationContext() {
        return invocationContext;
    }


    @Override
    public String toString() {
        return verbose();
    }

    public String verbose() {
        return super.verbose() +
            ", arguments=" + invocationContext.getArguments();
    }
}
