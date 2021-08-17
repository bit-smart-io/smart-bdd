package io.bitsmart.bdd.report.junit5.listeners;

import org.junit.platform.launcher.TestExecutionListener;

import static java.lang.System.getProperty;

public class SmartBddConfig implements TestExecutionListener {
    public static final String dataFolder = "io.bitsmart.bdd.report/data/";
    public static final String reportFolder = "io.bitsmart.bdd.report/report/";
    public static final String baseFolder = getProperty("java.io.tmpdir");

    public static String getDataFolder() {
        return dataFolder;
    }

    public static String getReportFolder() {
        return reportFolder;
    }

    public static String getBaseFolder() {
        return baseFolder;
    }
}
