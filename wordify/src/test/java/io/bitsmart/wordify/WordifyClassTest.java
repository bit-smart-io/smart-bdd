package io.bitsmart.wordify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WordifyClassTest {
    @Test
    void methodNoParams() {
        assertThat(true).isTrue();
    }

    //TODO are all params under test? i.e. wordify with format and highlight the fields under test
    @ParameterizedTest
    @ValueSource(strings = { "value 1" })
    void methodWithParams(String key) {
        assertThat(key).isNotNull();
    }

    //TODO handle unhappy path
    @Disabled
    @Test
    void wordifyMethodNotFound() {
        String wordify = new WordifyClass().wordify(this.getClass(), "doesNotExistMethod");
    }

    @Test
    void wordifyTestMethod() {
        String wordify = new WordifyClass().wordify(this.getClass(), "methodNoParams");
        assertThat(wordify).isEqualTo("Assert that true is true");
    }

    @Test
    void wordifyTestMethodWithParams() {
        String wordify = new WordifyClass().wordify(this.getClass(), "methodWithParams", Arrays.asList("value 1"));
        assertThat(wordify).isEqualTo("Assert that value 1 is not null");
    }

    // ut
    @Test
    void wordifyTestMethodWithUnderTest() {
        String wordify = new WordifyClass().wordify(this.getClass(), "methodWithParams", Arrays.asList("value 1"));
        assertThat(wordify).isEqualTo("Assert that value 1 is not null");
    }
}