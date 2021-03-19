package junit5.launch;

import junit5.extension.testwatcher.debug.DebugExtension;
import junit5.extension.testwatcher.results.ResultsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import results.junit.testcapture.CaptureTestClass;
import results.junit.testcapture.CaptureTestMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;

public class SelectMethodTest {

    /**
     * method no-args
     * re-run a specific including setup
     */
    @Test
    void selectAndRunMethodNoArgs() {
        ResultsExtension.reset();
        new TestLauncher().launch(
            new junit5.extension.utils.TestListener(),
            ClassUnderTest.class,
            "firstTest");

        assertThat(DebugExtension.getCapturedTestClasses().getClasses()).containsExactly("ClassUnderTest");
        CaptureTestClass capturedTestClass = DebugExtension.getCapturedTestClasses().getCapturedClasses().get("ClassUnderTest");
        assertThat(capturedTestClass).isNotNull();
        assertThat(capturedTestClass.getCapturedMethodsForClass().getCapturedMethodNames()).containsExactly(
            "beforeAll",
            "interceptBeforeAllMethod",
            "interceptTestClassConstructor", // firstTest
            "interceptAfterAllMethod",
            "afterAll"
        );

        assertThat(capturedTestClass.getMethodNames()).containsOnly(
            "firstTest"
        );

        CaptureTestMethod capturedFirstTestMethod = capturedTestClass.getCapturedTestMethod("firstTest");
        assertThat(capturedFirstTestMethod.getCapturedMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");
    }

    /**
     * method with args
     * re-run a specific including setup
     */
    @Disabled
    @Test
    void selectAndRunMethodWithArgs() {
        ResultsExtension.reset();
        new TestLauncher().launch(
            new junit5.extension.utils.TestListener(),
            selectMethod(ClassUnderTest.class, "thirdParamTest")
        );

        assertThat(DebugExtension.getCapturedTestClasses().getClasses()).containsExactly("ClassUnderTest");
        CaptureTestClass capturedTestClass = DebugExtension.getCapturedTestClasses().getCapturedClasses().get("ClassUnderTest");

        assertThat(capturedTestClass.getMethodNames()).containsOnly(
            "thirdParamTest"
        );
    }
}
