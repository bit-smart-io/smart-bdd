package io.bitsmart.bdd.report.junit5.listeners;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
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
public class SmartTestExecutionListener implements TestExecutionListener {
    private static final Logger logger = Logger.getLogger(SmartTestExecutionListener.class.getName());
    private List methodNames = new CopyOnWriteArrayList();

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        //logger.info("testPlanExecutionStarted");
        // init the ReportExtension?
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        //logger.info("testPlanExecutionFinished: " + methodNames + " " + testPlan.containsTests());
        ReportExtension.getTestContext().writeIndex();
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
        //logger.info("executionFinished");
    }

    @Override
    public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
    }
}
