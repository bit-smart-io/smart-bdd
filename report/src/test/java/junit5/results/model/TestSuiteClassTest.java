package junit5.results.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestSuiteClassTest {
    private final TestSuiteClass testSuiteClass = TestSuiteClass.testSuiteClass(TestSuiteClassTest.class);

    @Test
    void setsFullyQualifiedName() {
        assertThat(testSuiteClass.getFullyQualifiedName()).isEqualTo("junit5.results.model.TestSuiteClassTest");
    }

    @Test
    void setsClassName() {
        assertThat(testSuiteClass.getClassName()).isEqualTo("TestSuiteClassTest");
    }

    @Test
    void setsPackageName() {
        assertThat(testSuiteClass.getPackageName()).isEqualTo("junit5.results.model");
    }
}