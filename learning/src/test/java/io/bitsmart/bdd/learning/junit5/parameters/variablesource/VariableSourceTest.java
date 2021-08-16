package io.bitsmart.bdd.learning.junit5.parameters.variablesource;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * https://www.baeldung.com/parameterized-tests-junit-5
 * depends on testCompile 'org.junit.jupiter:junit-jupiter-params:5.3.1'
 */
public class VariableSourceTest {

    static Stream<Arguments> arguments = Stream.of(
        Arguments.of(null, true), // null strings should be considered blank
        Arguments.of("", true),
        Arguments.of("  ", true),
        Arguments.of("not blank", false)
    );

    @ParameterizedTest
    @VariableSource("arguments")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSource(String input, boolean expected) {
        assertEquals(expected, isBlank(input));
        assertThat(isBlank(input)).isEqualTo(expected);
    }

    private static boolean isBlank(String value) {
        return isEmpty(value) || isEmpty(value.trim());
    }

    private static boolean isEmpty(String value) {
        return value == null || value.equals("");
    }
}
