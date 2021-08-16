package io.bitsmart.bdd.learning.junit5.extension;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestAnnotationsExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    static boolean beforeAllCalled = false;

    @BeforeAll
    public static void beforeAll() {
        beforeAllCalled = true;
    }

    public static boolean isBeforeAllCalled() {
        return beforeAllCalled;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {}

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception { }

}
