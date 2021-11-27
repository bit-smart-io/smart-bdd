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
import io.bitsmart.bdd.report.utils.BuilderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SourceTokensBuilder implements Builder<JavaSourceTokens> {
    private List<TokenBuilder> tokens = new ArrayList<>();
    private int whiteSpace = 0;

    private SourceTokensBuilder() {
    }

    public static SourceTokensBuilder sourceTokens(TokenBuilder token) {
        return new SourceTokensBuilder().withToken(token);
    }

    public static SourceTokensBuilder sourceTokens(TokenBuilder... tokens) {
        return new SourceTokensBuilder().withTokens(tokens);
    }

    public SourceTokensBuilder withToken(TokenBuilder token) {
        this.tokens.add(token);
        return this;
    }

    public SourceTokensBuilder withTokens(TokenBuilder... tokens) {
        this.tokens.addAll(Arrays.asList(tokens));
        return this;
    }

    public SourceTokensBuilder withTokens(List<TokenBuilder> tokens) {
        this.tokens = tokens;
        return this;
    }

    public SourceTokensBuilder withWhiteSpace(int whiteSpace) {
        this.whiteSpace = whiteSpace;
        return this;
    }

    public JavaSourceTokens build() {
        return new JavaSourceTokens(BuilderUtils.build(tokens) , whiteSpace);
    }
}
