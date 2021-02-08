package wordify;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordifyClassTest {

    @Test
    void firstMethod() {
        assertThat(true).isTrue();
    }

    @Test
    void wordifyTestMethod() {
        final String wordify = new WordifyClass().wordify(this.getClass(), "firstMethod");
        assertThat(wordify).isEqualTo("assert that true is true");
    }
}