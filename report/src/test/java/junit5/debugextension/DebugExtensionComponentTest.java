package junit5.debugextension;

import junit5.debugextension.undertest.ClassUnderTest1;
import junit5.debugextension.utils.debugcapture.CaptureTestClass;
import junit5.debugextension.utils.debugcapture.CaptureTestMethod;
import junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class DebugExtensionComponentTest {

    @BeforeEach
    void setUp() {
        DebugExtension.reset();
    }

    @Test
    void debugTestsInClass() {
        TestLauncher.launch(ClassUnderTest1.class);

        assertThat(DebugExtension.getCapturedTestClasses().getClasses()).containsExactly("ClassUnderTest1");
        verifyClassUnderTestMethods("ClassUnderTest1", "class1_");
    }

    @Test
    void debugTestsInPackage() {
        TestLauncher.launch(selectPackage(ClassUnderTest1.class.getPackage().getName()));

        assertThat(DebugExtension.getCapturedTestClasses().getClasses()).containsExactly("ClassUnderTest1", "ClassUnderTest2");
        verifyClassUnderTestMethods("ClassUnderTest1", "class1_");
        verifyClassUnderTestMethods("ClassUnderTest2", "class2_");
    }

    private void verifyClassUnderTestMethods(String className, String methodPrefix) {
        CaptureTestClass capturedTestClass = DebugExtension.getCapturedTestClasses().getCapturedClasses().get(className);
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

        assertThat(capturedTestClass.getMethodNames()).containsExactly(
            methodPrefix + "firstTest",
            methodPrefix + "secondTest",
            methodPrefix + "thirdParamTest",
            methodPrefix + "thirdParamTest",
            methodPrefix + "thirdParamTest"
        );

        CaptureTestMethod capturedFirstTestMethod = capturedTestClass.getCapturedTestMethod(methodPrefix + "firstTest");
        assertThat(capturedFirstTestMethod.getCapturedMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");

        CaptureTestMethod capturedSecondTestMethod = capturedTestClass.getCapturedTestMethod(methodPrefix + "secondTest");
        assertThat(capturedSecondTestMethod.getCapturedMethodNames()).containsExactly(
            "beforeEach",
            "interceptBeforeEachMethod",
            "interceptTestMethod",
            "interceptAfterEachMethod",
            "afterEach");

        List<CaptureTestMethod> capturedThirdParamTestMethods = capturedTestClass.getCapturedTestMethods(methodPrefix + "thirdParamTest");
        capturedThirdParamTestMethods.forEach(captureTestMethod -> {
            assertThat(captureTestMethod.getCapturedMethodNames()).containsExactly(
                "beforeEach",
                "interceptBeforeEachMethod",
                "interceptTestTemplateMethod", // notice that this is interceptTestTemplateMethod
                "interceptAfterEachMethod",
                "afterEach");
        });
    }
}
