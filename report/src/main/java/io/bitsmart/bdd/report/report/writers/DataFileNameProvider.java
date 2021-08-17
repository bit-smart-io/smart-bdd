package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.junit5.listeners.SmartBddConfig;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFileNameProvider {

    public Path dataPath() {
        return Paths.get(SmartBddConfig.getBaseFolder(), SmartBddConfig.getDataFolder());
    }

    public Path dataIndex() {
        return dataPath().resolve("index.json");
    }

    public Path outputFile(TestSuite testSuite) {
        return outputFile(fullyQualifiedName(testSuite));
    }

    private String fullyQualifiedName(TestSuite testSuite) {
        return testSuite.getName();
    }

    private Path outputFile(String testName) {
        return dataPath().resolve("TEST-" + testName + ".json");
    }
}
