package io.bitsmart.bdd.learning.junit5.listeners;

import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.TestTag;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This has been added to resources/META-INF/services/
 */
public class GlobalTestExecutionListener implements TestExecutionListener {
    private static final Logger logger = Logger.getLogger(GlobalTestExecutionListener.class.getName());

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED) {
            //logger.warning("FAILED >>>> " + testIdentifier);
        } else {
            // now get the contents of the method
            //logger.info("SUCCESS >>>> " + testIdentifier);
            //wordify(testIdentifier);
        }
    }

    private void debugFields(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        String displayName = testIdentifier.getDisplayName();
        String legacyReportingName = testIdentifier.getLegacyReportingName();
        boolean test = testIdentifier.isTest();
        Optional<String> parentId = testIdentifier.getParentId();
        Optional<TestSource> source = testIdentifier.getSource();
        Set<TestTag> tags = testIdentifier.getTags();
        TestDescriptor.Type type = testIdentifier.getType();
        String uniqueId = testIdentifier.getUniqueId();
        TestExecutionResult.Status status = testExecutionResult.getStatus();
    }
}
