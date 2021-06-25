package io.bitsmart.bdd.report.junit5.learning.extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestAnnotationsExtension.class)
public class TestAnnotationsTest {

    @Test
    void firstTest() {
        assertThat(TestAnnotationsExtension.isBeforeAllCalled()).isFalse();
    }
}
