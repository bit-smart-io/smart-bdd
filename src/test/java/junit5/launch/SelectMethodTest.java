package junit5.launch;

import junit5.extension.testwatcher.debug.DebugExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import results.junit.testcapture.CaptureTestClass;
import results.junit.testcapture.CaptureTestMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;

public class SelectMethodTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;
    private static final String CLASS_UNDER_TEST_NAME = "ClassUnderTest";

    @BeforeEach
    void setUp() {
        DebugExtension.reset();
    }

    /**
     * method no-args
     * re-run a specific including setup
     */
    @Test
    void selectAndRunMethodNoArgs() {
        new TestLauncher().launch(
            new junit5.extension.utils.TestListener(),
            CLASS_UNDER_TEST,
            "firstTest");

        assertThat(DebugExtension.getCapturedTestClasses().getClasses()).containsExactly(CLASS_UNDER_TEST_NAME);
        CaptureTestClass capturedTestClass = DebugExtension.getCapturedTestClasses().getCapturedClasses().get(CLASS_UNDER_TEST_NAME);
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
        new TestLauncher().launch(
            new junit5.extension.utils.TestListener(),
            selectMethod(CLASS_UNDER_TEST, "thirdParamTest")
        );

        assertThat(DebugExtension.getCapturedTestClasses().getClasses()).containsExactly("TestCase");
        CaptureTestClass capturedTestClass = DebugExtension.getCapturedTestClasses().getCapturedClasses().get("TestCase");

        assertThat(capturedTestClass.getMethodNames()).containsOnly(
            "thirdParamTest"
        );
    }
}
