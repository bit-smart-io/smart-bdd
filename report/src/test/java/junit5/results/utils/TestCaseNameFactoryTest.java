package junit5.results.utils;

import junit5.results.model.TestCaseResult;
import junit5.results.model.TestCaseResultBuilder;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static junit5.results.model.TestCaseResultBuilder.aTestCaseResult;
import static org.assertj.core.api.Assertions.assertThat;

class TestCaseNameFactoryTest {

    private final TestCaseNameFactory testCaseNameFactory = new TestCaseNameFactory();

    @Test
    void createName_withEmptyArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName");
    }

    @Test
    void createName_withOneArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(singletonList("arg 1")).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName arg 1");
    }

    @Test
    void createName_withTwoArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(asList("arg 1", "arg 2")).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName arg 1, arg 2");
    }

    @Test
    void createName_withTwoIntegerArgs() {
        TestCaseResult testCaseResult = aDefaultTestCaseResult().withArgs(asList(1, 2)).build();
        assertThat(testCaseNameFactory.createName(testCaseResult)).isEqualTo("methodName 1, 2");
    }

    private TestCaseResultBuilder aDefaultTestCaseResult() {
        return aTestCaseResult()
            .withMethodName("methodName");
    }
}