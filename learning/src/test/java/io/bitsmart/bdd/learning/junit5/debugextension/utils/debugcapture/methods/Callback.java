package io.bitsmart.bdd.learning.junit5.debugextension.utils.debugcapture.methods;

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
