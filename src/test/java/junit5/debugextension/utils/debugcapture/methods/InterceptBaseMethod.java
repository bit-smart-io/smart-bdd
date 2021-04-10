package junit5.debugextension.utils.debugcapture.methods;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;

public class InterceptBaseMethod extends BaseMethod {
    private final InvocationInterceptor.Invocation<Void> invocation;
    private final ReflectiveInvocationContext<Method> invocationContext;

    public InterceptBaseMethod(
        String name,
        InvocationInterceptor.Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext,
        ExtensionContext extensionContext)
    {
        super(name, extensionContext);
        this.invocation = invocation;
        this.invocationContext = invocationContext;
    }

    public InvocationInterceptor.Invocation<Void> getInvocation() {
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
