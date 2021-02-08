package wordify;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordifyStringTest {
    // 'a,b'      = A b
    // 'a, b'     = A b
    // 'a,  b'    = A b
    // 'a(b,c)'   = A b c
    // 'a(b, c)'  = A b c
    // 'a (b, c)' = A b c
    // 'a (b, c)' = A b c
    // 'a(b.c,d)' = A b c d
    // 'aB'       = A b
    // 'aBCD'     = A BCD
    // 'aBCDEe'   = A BCD ee

    @Test
    void handlesWhiteSpaceCommaAndParentheses() {
        assertThat(new WordifyString("a,b").wordify()).isEqualTo("A b");
        assertThat(new WordifyString(("a, b")).wordify()).isEqualTo("A b");
        assertThat(new WordifyString(("a,  b")).wordify()).isEqualTo("A b");

        assertThat(new WordifyString(("a(b,c)")).wordify()).isEqualTo("A b c");
        assertThat(new WordifyString(("a(b, c)")).wordify()).isEqualTo("A b c");

        assertThat(new WordifyString(("a( b, c )")).wordify()).isEqualTo("A b c");
        assertThat(new WordifyString(("a ( b, c )")).wordify()).isEqualTo("A b c");
        assertThat(new WordifyString(("a  (  b  ,  c  )")).wordify()).isEqualTo("A b c");

        assertThat(new WordifyString((" a ( b, c )")).wordify()).isEqualTo("A b c");
    }

    @Test
    void handlesPeriod() {
        assertThat(new WordifyString("a.b").wordify()).isEqualTo("A b");
        assertThat(new WordifyString(("a. b")).wordify()).isEqualTo("A b");
        assertThat(new WordifyString(("a.  b")).wordify()).isEqualTo("A b");

        assertThat(new WordifyString(("a(b.c)")).wordify()).isEqualTo("A b c");
        assertThat(new WordifyString(("a(b. c)")).wordify()).isEqualTo("A b c");

        assertThat(new WordifyString(("a( b. c )")).wordify()).isEqualTo("A b c");
        assertThat(new WordifyString(("a ( b. c )")).wordify()).isEqualTo("A b c");
        assertThat(new WordifyString(("a  (  b  .  c  )")).wordify()).isEqualTo("A b c");

        assertThat(new WordifyString((" a ( b. c )")).wordify()).isEqualTo("A b c");
    }

    @Test
    void handlesWords() {
        assertThat(new WordifyString("aB").wordify()).isEqualTo("A b");
        assertThat(new WordifyString("aBcD").wordify()).isEqualTo("A bc d");
    }

    @Test
    void handlesSemiColon() {
        assertThat(new WordifyString("aB;").wordify()).isEqualTo("A b");
        assertThat(new WordifyString("aBcD;").wordify()).isEqualTo("A bc d");
    }

    @Test
    void handlesNumbers() throws Exception {
        assertThat(new WordifyString("-13.37").wordify()).isEqualTo(("-13.37"));
        assertThat(new WordifyString("-0").wordify()).isEqualTo(("-0"));
        assertThat(new WordifyString("-1").wordify()).isEqualTo(("-1"));
        assertThat(new WordifyString("-1 ").wordify()).isEqualTo(("-1"));
        assertThat(new WordifyString(" -1 ").wordify()).isEqualTo(("-1"));
        assertThat(new WordifyString("(-1)").wordify()).isEqualTo(("-1"));
        assertThat(new WordifyString(" ( -1 ) ").wordify()).isEqualTo(("-1"));
    }

    @Test
    void insertsASingleSpacesBetweenCapitalsAndTrims() {
         assertThat(new WordifyString("replaceAWithB;").wordify()).isEqualTo(("Replace a with b"));
         assertThat(new WordifyString("doesNotBlow").wordify()).isEqualTo(("Does not blow"));
    }

//    //TODO I can't think why you'd want this?
//    @Ignore
//    @Test
//    void wordifyShouldNotRemoveLineBreaks() {
//        assertThat(Text.wordify("F\nBar")).isEqualTo(("F\nBar"));
//        assertThat(Text.wordify("Foo\nBar")).isEqualTo(("Foo\nBar"));
//    }

    @Test
    void insertsSpaceOnFullStops() throws Exception {
        assertThat(new WordifyString("something.and(then.perhaps, something.else)").wordify())
            .isEqualTo(("Something and then perhaps something else"));
    }

    @Test
    void doesNotInsertSpaceOrRemoveDecimalPointForFloats() throws Exception {
        assertThat(new WordifyString("assertThat(sqrt(421.0704)).isEqualTo((20.520));").wordify())
            .isEqualTo(("Assert that sqrt 421.0704 is equal to 20.520"));
        assertThat(new WordifyString("13.37 66.6 3.33").wordify())
            .isEqualTo(("13.37 66.6 3.33"));
        assertThat(new WordifyString("someMethod(12.5,99, 22.22").wordify())
            .isEqualTo(("Some method 12.5 99 22.22"));
    }

    @Test
    void doesNotRemoveMinusSignForNumbers() throws Exception {
        assertThat(new WordifyString("-13.37").wordify()).isEqualTo(("-13.37"));
        assertThat(new WordifyString("-0").wordify()).isEqualTo(("-0"));
        assertThat(new WordifyString("-1").wordify()).isEqualTo(("-1"));
    }

    @Test
    void assertJStyleTests() throws Exception {
        assertThat(new WordifyString("assertThat(true).isEqualTo(true)").wordify()).isEqualTo(("Assert that true is equal to true"));
    }
}
