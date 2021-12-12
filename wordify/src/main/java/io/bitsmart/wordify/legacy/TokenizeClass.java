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

package io.bitsmart.wordify.legacy;

import io.bitsmart.wordify.MethodExtractor;
import io.bitsmart.wordify.sourcecode.MethodWrapper;
import io.bitsmart.wordify.sourcecode.ParameterWrapper;
import io.bitsmart.wordify.tokenize.JavaSourceTokens;
import io.bitsmart.wordify.tokenize.TokenizeParameter;
import io.bitsmart.wordify.tokenize.TokenizeParameterMap;
import io.bitsmart.wordify.tokenize.TokenizeSource;
import io.bitsmart.wordify.tokenize.TokenizeStringUtil;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;

@Deprecated
public class TokenizeClass {
    private static final Logger logger = Logger.getLogger(TokenizeClass.class.getName());
    private final MethodExtractor methodExtractor;

    public TokenizeClass(MethodExtractor methodExtractor) {
        this.methodExtractor = methodExtractor;
    }

    public String tokenizeAsString(Class<?> clazz, String methodName) {
        return tokenizeAsString(clazz, methodName, emptyList());
    }

    public String tokenizeAsString(Class<?> clazz, String methodName, List<Object> parameterValues) {
        JavaSourceTokens tokens = tokenize(clazz, methodName, parameterValues);
        return tokens.asString();
    }

    public JavaSourceTokens tokenize(Class<?> clazz, String methodName) {
        return tokenize(clazz, methodName, emptyList());
    }

    public JavaSourceTokens tokenize(Class<?> clazz, String methodName, List<Object> parameterValues) {
        MethodWrapper methodWrapper = methodExtractor.methodWrapper(clazz, methodName, parameterValues);
        List<ParameterWrapper> parameters = methodWrapper.getParameters();
        TokenizeParameterMap parameterMap = new TokenizeParameterMap();
        parameters.forEach(p -> {
            parameterMap.put(new TokenizeParameter(p.getParameter().getName(), p.getValue(), p.getParameter().getCanonicalName()));
        });

        String src = TokenizeStringUtil.stripJavaCode(methodWrapper.getSource());
        return new TokenizeSource(src, parameterMap).tokenize();
    }
}
