package io.bitsmart.bdd.learning.junit5.listeners;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * This has been added to resources/META-INF/services/
 */
public class GlobalTestExecutionListener implements TestExecutionListener {
    private static final Logger logger = Logger.getLogger(GlobalTestExecutionListener.class.getName());
    private List methodNames = new CopyOnWriteArrayList();

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        logger.info("testPlanExecutionStarted");
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        logger.info("testPlanExecutionFinished: " + methodNames.size());
    }

    @Override
    public void dynamicTestRegistered(TestIdentifier testIdentifier) {
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        methodNames.add(testIdentifier.getDisplayName());
        logger.info("executionFinished");
    }

    @Override
    public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
    }


//    @Override
//    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
//        if (testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED) {
//            //logger.warning("FAILED >>>> " + testIdentifier);
//        } else {
//            // now get the contents of the method
//            //logger.info("SUCCESS >>>> " + testIdentifier);
//            //wordify(testIdentifier);
//        }
//    }


//    private void debugFields(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
//        String displayName = testIdentifier.getDisplayName();
//        String legacyReportingName = testIdentifier.getLegacyReportingName();
//        boolean test = testIdentifier.isTest();
//        Optional<String> parentId = testIdentifier.getParentId();
//        Optional<TestSource> source = testIdentifier.getSource();
//        Set<TestTag> tags = testIdentifier.getTags();
//        TestDescriptor.Type type = testIdentifier.getType();
//        String uniqueId = testIdentifier.getUniqueId();
//        TestExecutionResult.Status status = testExecutionResult.getStatus();
//    }
}
