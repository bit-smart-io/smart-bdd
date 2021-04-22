package ft.report.oneclass;

import junit5.results.ReportFactory;
import junit5.results.ResultsExtension;
import junit5.utils.TestLauncher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import report.model.Report;
import report.model.Status;
import report.model.TestCase;
import report.model.TestSuite;
import shared.undertest.ClassUnderTest;

import static ft.report.builders.TestCaseBuilder.aTestCase;
import static ft.report.oneclass.ReportComponentBuilderWipTest.AssertTestCase.assertTestCase;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *  WIP new idea.
 *  Can have AssertTestSuiteForFirstTest. You can't modify any of the tests only add.
 */
public class ReportComponentBuilderWipTest {
    private static final Class<?> CLASS_UNDER_TEST = ClassUnderTest.class;

    @BeforeEach
    void setUp() {
        ResultsExtension.reset();
    }

    @Test
    void createReport() {
        TestLauncher.launch(CLASS_UNDER_TEST);

        Report report = ReportFactory.create(ResultsExtension.getAllResults());
        assertThat(report).isNotNull();
        assertThat(report.getTestCases()).hasSize(4);

        assertThat(report.getTestSuites()).hasSize(1);
        TestSuite testSuite = report.getTestSuites().get(0);

        // uses contains as the last statement
        AssertTestSuite.assertTestSuite(testSuite)
            .withName("shared.undertest.ClassUnderTest")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .withMethodNames("testMethod", "paramTest", "paramTest", "paramTest")
            .withTestCaseContains(firstTestCase());

        // uses contains as the last statement
        AssertTestSuite.assertTestSuite(testSuite)
            .withName("shared.undertest.ClassUnderTest")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .withMethodNames("testMethod", "paramTest", "paramTest", "paramTest")
            .withTestCase(
                assertTestCase(testSuite.getTestCases().get(0))
                    .withWordify("Passing assertion")
                    .withStatus(Status.PASSED)
                    .withClassName("ClassUnderTest")
                    .withPackageName("shared.undertest")
            );
    }

    static class AssertTestSuite {
        private final TestSuite testSuite;

        private AssertTestSuite(TestSuite testSuite) {
            this.testSuite = testSuite;
        }

        public static AssertTestSuite assertTestSuite(TestSuite testSuite) {
            return new AssertTestSuite(testSuite);
        }

        public AssertTestSuite withName(String name) {
            assertThat(testSuite.getName()).isEqualTo(name);
            return this;
        }

        public AssertTestSuite withClassName(String className) {
            assertThat(testSuite.getClassName()).isEqualTo(className);
            return this;
        }

        public AssertTestSuite withPackageName(String packageName) {
            assertThat(testSuite.getPackageName()).isEqualTo(packageName);
            return this;
        }

        public AssertTestSuite withMethodNames(String... methodNames) {
            assertThat(testSuite.getMethodNames()).containsExactly(methodNames);
            return this;
        }

        public AssertTestSuite withTestCaseContains(TestCase testCase) {
            assertThat(testSuite.getTestCases()).contains(testCase);
            return this;
        }

        public AssertTestSuite withTestCase(AssertTestCase assertTestCase) {
            return this;
        }
    }

    static class AssertTestCase {
        private final TestCase testCase;

        private AssertTestCase(TestCase testCase) {
            this.testCase = testCase;
        }

        public static AssertTestCase assertTestCase(TestCase testCase) {
            return new AssertTestCase(testCase);
        }

        public AssertTestCase withWordify(String wordify) {
            assertThat(testCase.getWordify()).isEqualTo(wordify);
            return this;
        }

        public AssertTestCase withStatus(Status status) {
            assertThat(testCase.getStatus()).isEqualTo(status);
            return this;
        }

        public AssertTestCase withClassName(String className) {
            assertThat(testCase.getClassName()).isEqualTo(className);
            return this;
        }

        public AssertTestCase withPackageName(String packageName) {
            assertThat(testCase.getPackageName()).isEqualTo(packageName);
            return this;
        }
    }

    private TestCase firstTestCase() {
        return aTestCase()
            .withWordify("Passing assertion")
            .withStatus(Status.PASSED)
            .withMethodName("testMethod")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest")
            .build();
    }
}
