package io.bitsmart.bdd.report.junit5.results.model;

import java.util.List;
import java.util.stream.Collectors;

public class TestCaseNameFactory {
    public String createName(TestCaseResult testCaseResult) {
        String methodName = testCaseResult.getMethodName();
        List<Object> args = testCaseResult.getArgs();
        if (args.size() == 0) {
            return methodName;
        }

        return testCaseResult.getMethodName() + args.stream().map(Object::toString).collect(Collectors.joining(", ", " ", ""));
    }
}
