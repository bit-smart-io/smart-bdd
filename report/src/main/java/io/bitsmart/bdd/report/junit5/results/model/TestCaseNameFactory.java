package io.bitsmart.bdd.report.junit5.results.model;

import java.util.List;
import java.util.stream.Collectors;

public class TestCaseNameFactory {
    public String createName(TestCaseResult testCaseResult) {
        String methodName = testCaseResult.getMethod().getName();
        List<Object> args = testCaseResult.getArgs();
        if (args.size() == 0) {
            return methodName;
        }

        return testCaseResult.getMethod().getName() + args.stream().map(Object::toString).collect(Collectors.joining(", ", " ", ""));
    }
}
