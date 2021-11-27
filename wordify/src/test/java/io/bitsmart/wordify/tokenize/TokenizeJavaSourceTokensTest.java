/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.bitsmart.wordify.tokenize;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.bitsmart.wordify.tokenize.TokenType.CHAR;
import static io.bitsmart.wordify.tokenize.TokenType.NUMBER;
import static io.bitsmart.wordify.tokenize.TokenType.STRING_LITERAL;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * End goal:
 * "wordify": "Passing assertion with string value 'blah' with int value 1",
 * "wordifyTemplate": "Passing assertion with string value '{{ 0 }}' with int value {{ 1 }} ",
 * "args": [
 * {
 * "type": "String",
 * "value": "blah"
 * },
 * {
 * "type": "Integer",
 * "value": 1
 * }
 * ],
 */
class TokenizeJavaSourceTokensTest {

    @Test
    void bookstoreFirstLine() {
        String input = "" +
            "        given(theIsbnDbContains().anEntry(\n";

        JavaSourceTokens javaSourceTokens = tokenizeSource(input);
        assertThat(javaSourceTokens.getWhiteSpace()).isEqualTo(8);
        List<Token> tokens = javaSourceTokens.getTokens();
        assertThat(tokens.get(0).asString()).isEqualTo("given");
        assertThat(tokens.get(1).asString()).isEqualTo("theIsbnDbContains");
        assertThat(tokens.get(2).asString()).isEqualTo("anEntry");
        assertThat(tokens.get(3).asString()).isEqualTo("\n");
        assertThat(tokens).hasSize(4);
    }

    /**
     * TODO address new line followed by white space
     * "\n    " -> TYPE_NEW_LINE and INDENT_LEVEL = 1
     */
    @Test
    void bookstore() {
        String input = "" +
            "        given(theIsbnDbContains().anEntry(\n" +
            "            forAnIsbn(\"default-isbn\")\n" +
            "                .thatWillReturn(anIsbnBook()\n" +
            "                    .withIsbn(\"default-isbn\")\n" +
            "                    .withTitle(\"default-title\")\n" +
            "                    .withAuthor(\"default-author\"))));\n" +
            "        when(aUserRequestsABook().withIsbn(\"default-isbn\"));\n" +
            "        then(theResponseContains(anIsbnBook()\n" +
            "            .withIsbn(\"default-isbn\")\n" +
            "            .withTitle(\"default-title\")\n" +
            "            .withAuthors(singletonList(\"default-author\"))));";

        JavaSourceTokens javaSourceTokens = tokenizeSource(input);
        assertThat(javaSourceTokens.getWhiteSpace()).isEqualTo(8);
        List<Token> tokens = javaSourceTokens.getTokens();
        assertThat(tokens.get(0).asString()).isEqualTo("given");
        assertThat(tokens.get(1).asString()).isEqualTo("theIsbnDbContains");
        assertThat(tokens.get(2).asString()).isEqualTo("anEntry");
        assertThat(tokens.get(3).asString()).isEqualTo("\n    ");
        assertThat(tokens.get(4).asString()).isEqualTo("forAnIsbn");
        assertThat(tokens.get(5)).isEqualTo(t("\"default-isbn\"", STRING_LITERAL));
        System.out.println(javaSourceTokens);
    }

    @Test
    void handlesEmptyLine() {
        assertEmptyTokenListStartingAt("", 0);
    }

    @Test
    void handlesWhiteSpaceAtStartOfLine() {
        assertEmptyTokenListStartingAt(" ", 1);
        assertEmptyTokenListStartingAt("  ", 2);
        assertEmptyTokenListStartingAt("\t", 4);
        assertEmptyTokenListStartingAt("\t ", 5);
        assertEmptyTokenListStartingAt(" \t", 5);
        assertEmptyTokenListStartingAt(" \t ", 6);
        assertTokenizeStartingAt(" a", 1, t("a"));
    }

    @Test
    void handlesFieldAndMethodsNames() {
        assertTokenize("doSomething();", t("doSomething"));
        assertTokenize("field.doSomething();", t("field"), t("doSomething"));
        assertTokenize("field. doSomething();", t("field"), t("doSomething"));
        assertTokenize("field .doSomething();", t("field"), t("doSomething"));
        assertTokenize("field . doSomething();", t("field"), t("doSomething"));
    }

    @Test
    void handlesFieldAndMethodsNames_multiLine() {
        assertTokenize("field\n.doSomething();", t("field"), t("\n"), t("doSomething"));
        assertTokenize("field. \ndoSomething();", t("field"), t("\n"), t("doSomething"));
        assertTokenize("field\n .doSomething();", t("field"), t("\n "), t("doSomething"));
        assertTokenize("field\n  .doSomething();", t("field"), t("\n  "), t("doSomething"));
    }

    @Test
    void handlesFieldAndMethodsNamesWithNumbers() {
        assertTokenize("doSomething99();", t("doSomething99"));
        assertTokenize("field99.doSomething99();", t("field99"), t("doSomething99"));
    }

    /**
     * TODO work out the requirements
     * 'a,b'      = A b
     * 'a, b'     = A b
     * 'a,  b'    = A b
     * 'a(b,c)'   = A b c
     * 'a(b, c)'  = A b c
     * 'a (b, c)' = A b c
     * 'a (b, c)' = A b c
     * 'a(b.c,d)' = A b c d
     * 'aB'       = A b
     * 'aBCD'     = A BCD
     * 'aBCDEe'   = A BCD ee
     */
    @Test
    void handlesFieldAndMethodsNamesCommas() {
        assertTokenize("a,b", t("a"), t("b"));
        assertTokenize("a, b", t("a"), t("b"));
        assertTokenize("a,  b", t("a"), t("b"));
        assertTokenize("a ,b", t("a"), t("b"));
        assertTokenize("a , b", t("a"), t("b"));
    }

    @Test
    void handlesParameters() {
        assertTokenize("a(b,c)", t("a"), t("b"), t("c"));
        assertTokenize("a(b,c.d())", t("a"), t("b"), t("c"), t("d"));

        // assertThat(new TokenizeSource("a,b").tokenize()).isEqualTo(new Line(asList(new Token("A"), new Token("b")), 0));
        // assertThat(new TokenizeSource(("a, b")).wordify()).isEqualTo("A b");
        // assertThat(new TokenizeSource(("a,  b")).wordify()).isEqualTo("A b");
        //
        // assertThat(new TokenizeSource(("a(b,c)")).wordify()).isEqualTo("A b c");
        // assertThat(new TokenizeSource(("a(b, c)")).wordify()).isEqualTo("A b c");
        //
        // assertThat(new TokenizeSource(("a( b, c )")).wordify()).isEqualTo("A b c");
        // assertThat(new TokenizeSource(("a ( b, c )")).wordify()).isEqualTo("A b c");
        // assertThat(new TokenizeSource(("a  (  b  ,  c  )")).wordify()).isEqualTo("A b c");
        //
        // assertThat(new TokenizeSource((" a ( b, c )")).wordify()).isEqualTo("A b c");
    }

    @Test
    void handlesQuotes() {
        assertTokenize("\"a\"", t("\"a\"", STRING_LITERAL));
        assertTokenize("(\"a\",\"b\")", t("\"a\"", STRING_LITERAL), t("\"b\"", STRING_LITERAL));
    }

    @Test
    void handlesEscapedQuotes() {
        assertTokenize("\"\\\"a\\\"\"", t("\"\\\"a\\\"\"", STRING_LITERAL));
    }

    @Test
    void handlesChars() {
        assertTokenize("char i = 'a'", t("char"), t("i"), t("'a'", CHAR));
        assertTokenize("('a','b')", t("'a'", CHAR), t("'b'", CHAR));
    }

    @Test
    void handlesNumbers() {
        assertTokenize("99.99", t("99.99", NUMBER));
        assertTokenize("doSomething(99.99);", t("doSomething"), t("99.99", NUMBER));
        assertTokenize("long i = 0;", t("long"), t("i"), t("0", NUMBER));
        assertTokenize("long i = 0L;", t("long"), t("i"), t("0", NUMBER));
        assertTokenize("float i = 0f;", t("float"), t("i"), t("0", NUMBER));
        assertTokenize("float i = 0.0f;", t("float"), t("i"), t("0.0", NUMBER));
        assertTokenize("char i = 0x0;", t("char"), t("i"), t("0x0", NUMBER));
        assertTokenize("char i = 0xF;", t("char"), t("i"), t("0xF", NUMBER));
    }

    @Disabled("This is the second pass")
    @Test
    void SecondPass_handlesFieldAndMethodsNames_multiLine() {
        assertTokenize("doSomething();", t("Do"), t("something"));
        assertTokenize("field\n.doSomething();", t("Field"), t("\n"), t("do"), t("something"));
        assertTokenize("field. \ndoSomething();", t("Field"), t("\n"), t("do"), t("something"));
        assertTokenize("field\n .doSomething();", t("Field"), t("\n"), t(" "), t("do"), t("something"));
        assertTokenize("field . doSomething();", t("Field"), t("do"), t("something"));
        assertTokenize("field . doSomething();", t("Field"), t("do"), t("something"));
    }

    /**
     * Maybe users will need an underscore
     * GivenIWant = Given I Want
     * IThinkITIsGreat = I think IT is great
     * ITIsGreat = IT is great
     * IWantToWorkInIT = I want to work in IT
     */
    @Disabled
    @Test
    void handlesCasesAndAcronyms() {
        assertTokenize("aB", t("A"), t("b"));
        assertTokenize("aBCD", t("A"), t("BCD"));
        assertTokenize("aBCDEe", t("A"), t("BCDE"), t("e"));
    }

    @Disabled
    @Test
    void LearningTestForLamdas() {
        String str1 = "roots.forEach(root -> logger.debug(\"tags: \" + root.getTags()));";
        String str2 = "logger.info(() -> \"message\"));";
        String str3 = "givenBuilder.build().getBooks().forEach((isbn, book) -> {";

        System.out.println(new TokenizeSource(str1).tokenize());
        System.out.println(new TokenizeSource(str2).tokenize());
        System.out.println(new TokenizeSource(str3).tokenize());

        System.out.println("as string: " + new TokenizeSource(str1).tokenize().asString());
        System.out.println("as string: " + new TokenizeSource(str2).tokenize().asString());
        System.out.println("as string: " + new TokenizeSource(str3).tokenize().asString());
    }

    private JavaSourceTokens tokenizeSource(String source) {
        return new TokenizeSource(source).tokenize();
    }

    private void assertEmptyTokenListStartingAt(String source, int lineStart) {
        assertThat(new TokenizeSource(source).tokenize()).isEqualTo(new JavaSourceTokens(Lists.emptyList(), lineStart));
    }

    private void assertTokenizeStartingAt(String source, int lineStart, Token... expected) {
        assertThat(new TokenizeSource(source).tokenize()).isEqualTo(new JavaSourceTokens(asList(expected), lineStart));
    }

    private void assertTokenize(String source, Token... expected) {
        assertThat(new TokenizeSource(source).tokenize()).isEqualTo(new JavaSourceTokens(asList(expected), 0));
    }

    private Token t(String source) {
        return new Token(source, TokenType.DEFAULT);
    }

    private Token t(String source, TokenType type) {
        return new Token(source, type);
    }
}