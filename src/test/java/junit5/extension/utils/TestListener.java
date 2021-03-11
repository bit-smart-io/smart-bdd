package junit5.extension.utils;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

public class TestListener implements TestExecutionListener {
    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            if (testExecutionResult.getStatus() == TestExecutionResult.Status.SUCCESSFUL) {
                // System.out.println("TEST SUCCESSFUL >>>> " + testIdentifier);
            }
        } else {
            // System.out.println("CONTAINER >>>> " + testIdentifier);
        }
    }
}