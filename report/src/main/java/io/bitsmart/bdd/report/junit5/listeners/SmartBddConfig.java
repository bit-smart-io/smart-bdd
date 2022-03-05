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

package io.bitsmart.bdd.report.junit5.listeners;

import org.junit.platform.launcher.TestExecutionListener;

import static java.lang.System.getProperty;

public class SmartBddConfig implements TestExecutionListener {
    public static final String dataFolder = "io.bitsmart.bdd.report/data/";
    public static final String reportFolder = "io.bitsmart.bdd.report/report/";
    public static String baseFolder = getProperty("java.io.tmpdir");

    public static void setBaseFolder(String folder) {
        baseFolder = folder;
    }

    public static void resetBaseFolder() {
        baseFolder = getProperty("java.io.tmpdir");
    }

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
