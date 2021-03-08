package junit5.extension.testwatcher.debug;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import results.junit.testcapture.CaptureTestClass;
import results.junit.testcapture.CaptureTestMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDebugLauncherTest {

    @Test
    void launchTests() {
        TestDebugLauncher launcher = new TestDebugLauncher();
        TestListener testListener = new TestListener();
        launcher.launch(testListener);
        assertThat(TestDebugWatcher.getCapturedTestClasses().getClasses()).containsExactly("TestDebugWatcherTest");

        CaptureTestClass capturedTestClass = TestDebugWatcher.getCapturedTestClasses().getCapturedClasses().get("TestDebugWatcherTest");
        assertThat(capturedTestClass).isNotNull();
        assertThat(capturedTestClass.getCapturedMethodsForClass().getCapturedMethodNames()).containsExactly(
            "beforeAll",
            "interceptBeforeAllMethod",
            "interceptTestClassConstructor", // firstTest
            "interceptTestClassConstructor", // secondTest
            "interceptTestClassConstructor", // thirdParamTest param 1
            "interceptTestClassConstructor", // thirdParamTest param 2
            "interceptTestClassConstructor", // thirdParamTest param 3
            "interceptAfterAllMethod",
            "afterAll"
        );

        assertThat(capturedTestClass.getMethodNames()).contains(
            "firstTest",
            "secondTest",
            "thirdParamTest"
        );

        CaptureTestMethod capturedFirstTestMethod = capturedTestClass.getCapturedTestMethod("firstTest");
        assertThat(capturedFirstTestMethod.getCapturedMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");

        CaptureTestMethod capturedSecondTestMethod = capturedTestClass.getCapturedTestMethod("firstTest");
        assertThat(capturedSecondTestMethod.getCapturedMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");

        List<CaptureTestMethod> capturedThirdParamTestMethods = capturedTestClass.getCapturedTestMethods("thirdParamTest");
        capturedThirdParamTestMethods.forEach(captureTestMethod -> {
            assertThat(captureTestMethod.getCapturedMethodNames()).containsExactly(
                "beforeEach",
                "interceptBeforeEachMethod",
                "interceptTestTemplateMethod", // notice that this is interceptTestTemplateMethod
                "interceptAfterEachMethod",
                "afterEach");
        });

    }

    static class TestListener implements TestExecutionListener {
        @Override
        public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
            if (testIdentifier.isTest()) {
                if (testExecutionResult.getStatus() == Status.SUCCESSFUL) {
                    // System.out.println("TEST SUCCESSFUL >>>> " + testIdentifier);
                }
            } else {
                    // System.out.println("CONTAINER >>>> " + testIdentifier);
            }
        }
    }
}
