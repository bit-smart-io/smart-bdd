package results.junit.testcapture;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CaptureTestClasses {
    private ConcurrentHashMap<String, CaptureTestClass> capturedClasses = new ConcurrentHashMap();

    public List<String> getClasses() {
        return Collections.list(capturedClasses.keys());
    }

    public ConcurrentHashMap<String, CaptureTestClass> getCapturedClasses() {
        return capturedClasses;
    }

    public CaptureTestClass getCaptureTestClass(ExtensionContext extensionContext) {
        return capturedClasses.get(getClassName(extensionContext));
    }

    public CaptureTestClass newCaptureTestClass(ExtensionContext context) {
        CaptureTestClass captureTestClass = new CaptureTestClass();
        capturedClasses.put(getClassName(context), captureTestClass);
        return captureTestClass;
    }

    public String getClassName(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestClass().getSimpleName();
    }
}
