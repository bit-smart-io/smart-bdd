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

public class WordifyStringUtil {

    public static String wordifyMethodOrFieldName(String input) {
        return wordifyMethodOrFieldName(input.toCharArray(), 0, input.length());
    }

    public static String wordifyMethodOrFieldName(char[] input, int from, int to) {
        StringBuilder stringBuilder = new StringBuilder();
        char ch;
        for (int i = from; i < to; i++) {
            ch = input[i];

            if (Character.isUpperCase(ch)) {
                stringBuilder.append(" ").append(Character.toLowerCase(ch));
            } else if (ch == '_') {
                if (i == 0) {
                } else if (i == to - 1) {
                } else {
                    stringBuilder.append(" ");
                }
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public static String upperCaseFirstChar(String input) {
        if (input.length() == 0) {
            return input;
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
    }
}
