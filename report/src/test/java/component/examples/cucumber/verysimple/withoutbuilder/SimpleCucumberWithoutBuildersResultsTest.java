package component.examples.cucumber.verysimple.withoutbuilder;

import component.results.AbstractResultsForClass;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCucumberWithoutBuildersResultsTest extends AbstractResultsForClass {

    @Override
    public Class<?> classUnderTest() {
        return SimpleCucumberWithoutBuildersTest.class;
    }

    @Test
    void launchTests() {
        assertThat(testCase(0).getWordify()).isEqualTo(
            "Given there are cucumbers 12 \n" +
            "When i eat cucumbers 5 \n" +
            "Then i should have cucumbers 7");
    }
}
