package io.bitsmart.bdd.report.junit5.launcher;

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
        launch(selector);
    }

    public static void launch(Class<?> clazz, String methodName) {
        DiscoverySelector selector = selectMethod(clazz, methodName);
        launch(selector);
    }

    public static void launch(DiscoverySelector... selectors) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(selectors)
           // this would only work if we launched via this Launcher
           // .configurationParameter("junit.platform.output.capture.stdout", "true")
           // .configurationParameter("junit.platform.output.capture.stderr", "true")
            .build();

        Launcher launcher = LauncherFactory.create();
        launcher.execute(request);
    }
}
