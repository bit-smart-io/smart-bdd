package junit5.launch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Tests {

    @ParameterizedTest
    @MethodSource("userGenerator")
    void testSuccess(String name, String pw) {
        Assertions.assertEquals(name, pw);
    }

    static Stream<Arguments> userGenerator() {
        return Stream.of(Arguments.of("password", "password"));
    }
}