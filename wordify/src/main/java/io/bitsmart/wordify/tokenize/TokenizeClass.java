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

import io.bitsmart.wordify.MethodExtractor;
import io.bitsmart.wordify.sourcecode.MethodWrapper;
import io.bitsmart.wordify.sourcecode.ParameterWrapper;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;

public class TokenizeClass {
    private static final Logger logger = Logger.getLogger(TokenizeClass.class.getName());
    private final MethodExtractor methodExtractor;

    public TokenizeClass(MethodExtractor methodExtractor) {
        this.methodExtractor = methodExtractor;
    }

    public List<JavaSourceTokens> tokenize(Class<?> clazz, String methodName) {
        return tokenize(clazz, methodName, emptyList());
    }

    public List<JavaSourceTokens> tokenize(Class<?> clazz, String methodName, List<Object> parameterValues) {
        MethodWrapper methodWrapper = methodExtractor.methodWrapper(clazz, methodName, parameterValues);

        //ParametersWrapper
        List<ParameterWrapper> parameters = methodWrapper.getParameters();

        // TokenizeSource(source, parameters) when it matches a parameter that has the same name it can mark it.
        // i.e. parameters = name: bob. name(name) = name:method, bob:argument or printName(name) = printName:method, bob:argument
        // i.e. parameters = []. printName(name) = printName:method, name:parameter

        // should line be source or code or sourceCode
        new TokenizeSource(methodWrapper.getSource()).tokenize();
        return null;

//        try {
//            JavaSourceWrapper sourceWrapper = new JavaSourceWrapper(clazz);
//            List<JavaMethod> allMethods = sourceWrapper.getMethods();
//            List<JavaMethod> matchedMethods = allMethods.stream().filter(m -> m.getName().contains(methodName)).collect(toList());
//            JavaMethod matchedMethod = matchedMethods.get(0);
//            String sourceCode = matchedMethod.getSourceCode();
//            List<JavaParameter> parameters = sourceWrapper.getParams(matchedMethod.getName());
//            sourceCode = updateSourceCode(sourceCode, parameters, parameterValues);
//            String wordify = new WordifyString(sourceCode).wordify();
//            return wordify;
//        } catch (IOException e) {
//            logger.log(Level.WARNING, "Could not load Java source", e);
//            return "Could not load Java source: " + e;
//        }
    }

//    private String updateSourceCode(String sourceCode, List<JavaParameter> parameters, List<Object> parameterValues) {
//        try {
//            int count = 0;
//            for (JavaParameter param : parameters) {
//                String value = parameterValues.get(count) == null ? "null" : parameterValues.get(count).toString();
//                sourceCode = sourceCode.replace(param.getName(), value);
//                count++;
//            }
//            return sourceCode;
//        } catch (Exception e) {
//            System.out.println("WordifyClass Error - parameters: " + parameters + ", parameterValues: " + parameterValues + ", sourceCode: " + sourceCode);
//            return sourceCode;
//        }
//    }
}
