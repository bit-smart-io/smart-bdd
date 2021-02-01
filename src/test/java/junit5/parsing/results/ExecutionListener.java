package junit5.parsing.results;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.logging.Logger;

public class ExecutionListener implements TestExecutionListener {
    private static final Logger logger = Logger.getLogger(ExecutionListener.class.getName());

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED) {
            //logger.warning("FAILED >>>> " + testIdentifier);
        } else {
            // now get the contents of the method
            //logger.info("SUCCESS >>>> " + testIdentifier);
        }
    }
}
