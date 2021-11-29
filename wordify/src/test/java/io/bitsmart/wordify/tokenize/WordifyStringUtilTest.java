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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class WordifyStringUtilTest {

    @Test
    void wordifyCharArray() {
        char[] chars = "doSomething".toCharArray();
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName(chars, 0, chars.length)).isEqualTo("do something");

        chars = "do_something".toCharArray();
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName(chars, 0, chars.length)).isEqualTo("do something");

        chars = "do_something_".toCharArray();
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName(chars, 0, chars.length)).isEqualTo("do something");

        chars = "_do_something_".toCharArray();
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName(chars, 0, chars.length)).isEqualTo("do something");
    }

    @Test
    void wordifyString() {
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName("doSomething")).isEqualTo("do something");
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName("do_something")).isEqualTo("do something");
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName("do_something_")).isEqualTo("do something");
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName("_do_something_")).isEqualTo("do something");
    }

    @Disabled
    @Test
    void wordifyMoreThanOneUnderscoreInARow() {
        char[] chars = "__do__something__".toCharArray();
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName(chars, 0, chars.length)).isEqualTo("do something");
    }

    @Test
    void upperCaseFirstChar_emptyString() {
        assertThat(WordifyStringUtil.upperCaseFirstChar("")).isEqualTo("");
    }

    @Test
    void upperCaseFirstChar() {
        assertThat(WordifyStringUtil.upperCaseFirstChar("String")).isEqualTo("String");
        assertThat(WordifyStringUtil.upperCaseFirstChar("string")).isEqualTo("String");
    }

    /**
     * Not sure what to do
     * 'aB'       = A b
     * 'aBCD'     = A BCD
     * 'aBCDEe'   = A BCD ee
     */
    @Disabled
    @Test
    void wordifyAcronyms() {
        char[] chars = "__do__something__".toCharArray();
        assertThat(WordifyStringUtil.wordifyMethodOrFieldName(chars, 0, chars.length)).isEqualTo("do something");
    }

    /**
     *  doSomething99" t("do something 99"));
     * */

    /**
     *  DoSomething" t("Do something 99"));
     * */
}