package junit5.parsing;

import com.thoughtworks.qdox.model.JavaMethod;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.TestTag;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import source.JavaSourceWrapper;
import wordify.WordifyClass;
import wordify.WordifyString;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

public class ExecutionListener implements TestExecutionListener {
    private static final Logger logger = Logger.getLogger(ExecutionListener.class.getName());

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testExecutionResult.getStatus() == TestExecutionResult.Status.FAILED) {
            //logger.warning("FAILED >>>> " + testIdentifier);
        } else {
            // now get the contents of the method
            //logger.info("SUCCESS >>>> " + testIdentifier);
            wordify(testIdentifier);
        }
    }

    private void wordify(TestIdentifier testIdentifier) {
        testIdentifier.getSource().ifPresent(this::wordify);
    }

    private void wordify(TestSource source) {
        MethodSource methodSource = null;
        if (source instanceof MethodSource) {
            methodSource = (MethodSource) source;
        } else {
            //logger.info(">>>> testSource not from method:" + source);
            return;
        }

        //Method javaMethod = methodSource.getJavaMethod();
        String wordify = new WordifyClass().wordify(methodSource.getJavaClass(),  methodSource.getMethodName());
        //logger.info(">>>> wordify: " + wordify);
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
