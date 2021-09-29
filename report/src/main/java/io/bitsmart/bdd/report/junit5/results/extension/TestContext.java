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

package io.bitsmart.bdd.report.junit5.results.extension;

import io.bitsmart.bdd.report.junit5.results.model.TestCaseNameFactory;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.report.writers.ReportWriter;
import io.bitsmart.wordify.WordifyExtensionContext;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.ABORTED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.DISABLED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.FAILED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.PASSED;

/**
 *
 * Possible todo
 *   private static final LocalDateTime dateTime = LocalDateTime.now();
 *   private static final ReportWriter reportWriter = new ReportWriter(dateTime);
 */
public class TestContext implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {
    private final TestResults testResults;
    private final WordifyExtensionContext wordifyExtensionContext;
    private final TestCaseNameFactory testCaseNameFactory;
    private final ReportWriter reportWriter;
    private boolean isReporting = false;

    public TestContext(
        TestResults testResults,
        WordifyExtensionContext wordifyExtensionContext,
        TestCaseNameFactory testCaseNameFactory,
        ReportWriter reportWriter)
    {
        this.testResults = testResults;
        this.wordifyExtensionContext = wordifyExtensionContext;
        this.testCaseNameFactory = testCaseNameFactory;
        this.reportWriter = reportWriter;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        startReporting();
        testResults.startTestSuite(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        getTestSuiteResult(context).startTestCase(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        getTestSuiteResult(context).completeTestSuite();
        //writeTestSuiteResults();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
    }

    @Override
    public <T> T interceptTestClassConstructor(Invocation<T> invocation, ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    /** normal test without params */
    @Override
    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        updateTestCaseResult(invocationContext, extensionContext);
        invocation.proceed();
    }

    /**
     * Using (at)TestFactory??? Not tested.
     * */
    @Override
    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        updateTestCaseResult(invocationContext, extensionContext);
        return invocation.proceed();
    }

    /**
     * Pass in params:
     * (at)ParameterizedTest
     * (at)ValueSource(strings = { "value 1", "value 2", "value 3" })
     * void paramTest(String param) {
     *     passingAssertionWith(param);
     * }
     */
    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        updateTestCaseResult(invocationContext, extensionContext);
        invocation.proceed();
    }

    /**
     * Using (at)TestFactory??? Not tested.
     */
    @Override
    public void interceptDynamicTest(Invocation<Void> invocation, ExtensionContext extensionContext) throws Throwable {
        // no invocationContext???
        //updateTestCaseResult(invocationContext, extensionContext);
        invocation.proceed();
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        getTestCaseResult(context).setStatus(PASSED);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        getTestSuiteResult(context).startTestCase(context).setStatus(DISABLED);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        getTestCaseResult(context).setStatus(ABORTED).setCause(cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        getTestCaseResult(context).setStatus(FAILED).setCause(cause);
    }

    private void startReporting() {
        if (!isReporting) {
            reportWriter.prepare();
        }
        isReporting = true;
    }

    public void writeTestSuiteResults() {
        getTestResults().getTestSuiteResults().forEach((reportWriter::write));
    }

    public void writeIndex() {
        if (isReporting) {
            reportWriter.write(getTestResults());
        }
    }

    public TestResults getTestResults() {
        return testResults;
    }

    public TestSuiteResult getTestSuiteResult(ExtensionContext context) {
        return testResults.getTestResultsForClass(context);
    }

    public TestCaseResult getTestCaseResult(ExtensionContext context) {
        TestSuiteResult testSuiteResult = getTestSuiteResult(context);
        return testSuiteResult.getTestCaseResult(context);
    }

    public void updateTestCaseResult(ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) {
        TestCaseResult testCaseResult = getTestCaseResult(extensionContext);
//        List<Object> arguments = invocationContext.getArguments();
//        Method method = invocationContext.getExecutable();
//        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//        String displayName = extensionContext.getDisplayName();

        wordifyExtensionContext.wordify(extensionContext, invocationContext.getArguments()).ifPresent(testCaseResult::setWordify);
        testCaseResult.setArgs(invocationContext.getArguments());
        testCaseResult.setName(testCaseNameFactory.createName(testCaseResult));
        // testCaseResult.setName(extensionContext.getDisplayName());
    }

    public void reset() {
        testResults.reset();
    }
}
