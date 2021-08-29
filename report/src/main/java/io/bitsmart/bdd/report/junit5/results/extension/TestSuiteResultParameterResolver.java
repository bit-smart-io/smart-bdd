package io.bitsmart.bdd.report.junit5.results.extension;

import io.bitsmart.bdd.report.junit5.annotations.InjectTestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

public class TestSuiteResultParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(InjectTestSuiteResult.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        TestResults testResults = ReportExtension.getTestContext().getTestResults();
        return testResults.getTestResultsForClass(extensionContext);
    }
}
