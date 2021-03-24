package junit5.results.debugcapture.methods;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;

public class InterceptTestFactoryBaseMethod<T> extends BaseMethod {
    private final InvocationInterceptor.Invocation<T> invocation;
    private final ReflectiveInvocationContext<Method> invocationContext;

    public InterceptTestFactoryBaseMethod(
        String name,
        InvocationInterceptor.Invocation<T> invocation,
        ReflectiveInvocationContext<Method> invocationContext,
        ExtensionContext extensionContext
    ) {
        super(name, extensionContext);
        this.invocation = invocation;
        this.invocationContext = invocationContext;
    }

    public InvocationInterceptor.Invocation<T> getInvocation() {
        return invocation;
    }

    public ReflectiveInvocationContext<Method> getInvocationContext() {
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
