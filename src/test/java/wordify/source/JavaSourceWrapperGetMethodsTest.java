package wordify.source;

import org.junit.jupiter.api.Test;
import wordify.JavaSourceWrapper;

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