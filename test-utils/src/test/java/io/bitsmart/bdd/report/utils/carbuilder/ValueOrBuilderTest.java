package io.bitsmart.bdd.report.utils.carbuilder;

import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.ValueOrBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValueOrBuilderTest {

    @Test
    void getStringValue() {
        ValueOrBuilder<String> string = new ValueOrBuilder<>("value");
        assertThat(string.get()).isEqualTo("value");
    }

    @Test
    void getStringValueFromBuilder() {
        ValueOrBuilder<String> string = new ValueOrBuilder<>(new StringBuilder());
        assertThat(string.get()).isEqualTo("built value");
    }

    @Test
    void handlesNullBuilder() {
        StringBuilder stringBuilder = null;
        ValueOrBuilder<String> string = new ValueOrBuilder<>(stringBuilder);
        assertThat(string.get()).isEqualTo(null);
    }

    @Test
    void handlesNullValue() {
        String stringValue = null;
        ValueOrBuilder<String> string = new ValueOrBuilder<>(stringValue);
        assertThat(string.get()).isEqualTo(null);
    }

    class StringBuilder implements Builder<String> {

        @Override
        public String build() {
            return "built value";
        }
    }
}

