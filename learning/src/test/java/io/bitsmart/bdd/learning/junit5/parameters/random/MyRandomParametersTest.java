package io.bitsmart.bdd.learning.junit5.parameters.random;

import io.bitsmart.bdd.learning.junit5.parameters.random.RandomParametersExtension.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomParametersExtension.class)
class MyRandomParametersTest {

    @Test
    void injectsInteger(@Random int i, @Random int j) {
        assertThat(i).isNotEqualTo(j);
    }

    @Test
    void injectsDouble(@Random double d) {

    }
}