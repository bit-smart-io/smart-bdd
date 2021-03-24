package junit5.results.debugcapture.methods;

import org.junit.jupiter.api.extension.ExtensionContext;

public class Callback extends BaseMethod {
    public Callback(
        String name,
        ExtensionContext extensionContext) {
        super(name, extensionContext);
    }


    @Override
    public String toString() {
        return verbose();
    }
}
