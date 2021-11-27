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

import io.bitsmart.bdd.report.utils.Builder;

public final class TokenBuilder implements Builder<Token> {
    private String value;
    private TokenType type;

    private TokenBuilder() {
    }

    public static TokenBuilder token(String value) {
        return new TokenBuilder().withValue(value);
    }

    public static TokenBuilder token(String value, TokenType type) {
        return new TokenBuilder().withValue(value).withType(type);
    }

    public TokenBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public TokenBuilder withType(TokenType type) {
        this.type = type;
        return this;
    }

    public Token build() {
        return new Token(value, type);
    }
}
