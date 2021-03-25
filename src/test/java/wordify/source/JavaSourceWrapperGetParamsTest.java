package wordify.source;

import com.thoughtworks.qdox.model.JavaParameter;
import org.junit.jupiter.api.Test;
import wordify.JavaSourceWrapper;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JavaSourceWrapperGetParamsTest {

    public void methodWithParams(String param1) {
    }

    @Test
    void returnsMethodsFromClass() throws IOException {
        JavaSourceWrapper javaSourceWrapper = new JavaSourceWrapper(this.getClass());
        List<JavaParameter> params = javaSourceWrapper.getParams("methodWithParams");
        assertThat(params).hasSize(1);
        assertThat(params.get(0).getName()).isEqualTo("param1");
    }
}