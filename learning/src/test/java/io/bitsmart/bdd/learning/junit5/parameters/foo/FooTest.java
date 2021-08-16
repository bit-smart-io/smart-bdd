package io.bitsmart.bdd.learning.junit5.parameters.foo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

//https://www.baeldung.com/junit-5-parameters
@ExtendWith(FooParameterResolver.class)
public class FooTest {

    @Test
    void firstTest() {
        assertThat(true).isTrue();
    }

    @Test
    void fooTest(Foo foo) {
        assertThat(foo).isEqualTo(new Foo("some name"));
    }
}
