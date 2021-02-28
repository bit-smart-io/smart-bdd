package junit5.extension.testwatcher.debug;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import results.junit.testcapture.CaptureTestClass;
import results.junit.testcapture.CaptureTestMethod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDebugLauncherTest {

    @Test
    void launchTests() {
        TestDebugLauncher launcher = new TestDebugLauncher();
        TestListener testListener = new TestListener();
        launcher.launch(testListener);
        //testListener.getSoftAssertions().assertAll();

        assertThat(TestDebugWatcher.getCapturedTestClasses().getClasses()).containsExactly("TestDebugWatcherTest");

        final CaptureTestClass captureTestClass = TestDebugWatcher.getCapturedTestClasses().getCapturedClasses().get("TestDebugWatcherTest");
        assertThat(captureTestClass).isNotNull();
        assertThat(captureTestClass.getCapturedMethodsForClass().getMethodNames()).containsExactly(
            "beforeAll",
            "interceptBeforeAllMethod",
            "interceptTestClassConstructor",
            "interceptTestClassConstructor",
            "interceptAfterAllMethod",
            "afterAll"
        );

        //TODO capturedCallbacksForTestMethods
        //OR   capturedEventsForTestMethods
        ConcurrentHashMap<String, CaptureTestMethod> capturedTestMethods = captureTestClass.getCapturedMethodsForTestMethods();
        assertThat(Collections.list(capturedTestMethods.keys())).contains(
            "firstTest",
            "secondTest"
        );

        assertThat(capturedTestMethods.get("firstTest")).isNotNull();
        assertThat(capturedTestMethods.get("firstTest").getMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");
        assertThat(capturedTestMethods.get("secondTest").getMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");
    }

    static class TestListener implements TestExecutionListener {
        private SoftAssertions softAssertions = new SoftAssertions();

        public SoftAssertions getSoftAssertions() {
            return softAssertions;
        }

        @Override
        public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
            if (testIdentifier.isTest()) {
                if (testExecutionResult.getStatus() == Status.SUCCESSFUL) {
                    List<String> allMethodNames = TestDebugWatcher.getAllMethodNames();

                    if (testIdentifier.getDisplayName().equals("firstTest()")) {
                        softAssertions.assertThat(allMethodNames).containsExactly(
                            "beforeAll",
                            "interceptBeforeAllMethod",
                            "interceptTestClassConstructor",
                            "beforeEach",
                            "interceptBeforeEachMethod",
                            "interceptTestMethod",
                            "interceptAfterEachMethod",
                            "afterEach"
                        );
                    } else if (testIdentifier.getDisplayName().equals("secondTest()")) {
                        softAssertions.assertThat(allMethodNames).containsExactly(
                            "beforeAll",
                            "interceptBeforeAllMethod",
                            "interceptTestClassConstructor",
                            "beforeEach",
                            "interceptBeforeEachMethod",
                            "interceptTestMethod",
                            "interceptAfterEachMethod",
                            "afterEach",
                            "interceptTestClassConstructor",
                            "beforeEach",
                            "interceptBeforeEachMethod",
                            "interceptTestMethod",
                            "interceptAfterEachMethod",
                            "afterEach"
                        );
                    }
                }
            } else {
//                System.out.println("CONTAINER >>>> " + testIdentifier);
            }
        }
    }
}
