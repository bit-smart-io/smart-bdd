package component.results;

import org.junit.jupiter.api.Test;
import shared.undertest.ClassUnderTest;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSuiteClassResultsTest extends AbstractResultsForTestSuite  {

    @Override
    public Class<?> classUnderTest() {
        return ClassUnderTest.class;
    }

    @Test
    void verifyTestSuiteClass() {
        assertThat(testSuiteClass().getFullyQualifiedName()).isEqualTo("shared.undertest.ClassUnderTest");
        assertThat(testSuiteClass().getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuiteClass().getPackageName()).isEqualTo("shared.undertest");
    }
}
