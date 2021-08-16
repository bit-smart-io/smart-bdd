package io.bitsmart.bdd.learning.junit5.launcher;

import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;

/**
 * Wrapper for selecting and launching JUnit tests.
 */
public class TestLauncher {

    public static void launch(Class<?> clazz) {
        DiscoverySelector selector = selectClass(clazz);
        launch(new TestListener(), selector);
    }

    public static void launch(TestExecutionListener executionListener, Class<?> clazz) {
        DiscoverySelector selector = selectClass(clazz);
        launch(executionListener, selector);
    }

    public static void launch(TestExecutionListener executionListener, Class<?> clazz, String methodName) {
        DiscoverySelector selector = selectMethod(clazz, methodName);
        launch(executionListener, selector);
    }

    public static void launch(Class<?> clazz, String methodName) {
        DiscoverySelector selector = selectMethod(clazz, methodName);
        launch(new TestListener(), selector);
    }

    public static void launch(DiscoverySelector... selectors) {
        launch(new TestListener(), selectors);
    }

    public static void launch(TestExecutionListener executionListener, DiscoverySelector... selectors) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(selectors)
           // this would only work if we launched via this Launcher
           // .configurationParameter("junit.platform.output.capture.stdout", "true")
           // .configurationParameter("junit.platform.output.capture.stderr", "true")
            .build();

        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        //launcher.registerTestExecutionListeners(listener, executionListener);

        launcher.execute(request);

        // TestExecutionSummary summary = listener.getSummary();
        // summary.printTo(new PrintWriter(System.err));
    }
}
