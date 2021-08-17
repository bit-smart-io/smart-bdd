package io.bitsmart.wordify.source;

import io.bitsmart.wordify.JavaSourceWrapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class JavaSourceWrapperGetMethodsTest {

    @Test
    void returnsMethodsFromClass() throws IOException {
        JavaSourceWrapper javaSourceWrapper = new JavaSourceWrapper(this.getClass());
        assertThat(javaSourceWrapper.getMethods()).hasSize(1);
        assertThat(javaSourceWrapper.getMethods().get(0).getName()).isEqualTo("returnsMethodsFromClass");
    }
}