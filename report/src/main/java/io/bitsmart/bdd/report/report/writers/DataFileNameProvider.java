package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.junit5.listeners.SmartBddConfig;
import io.bitsmart.bdd.report.report.model.TestSuite;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFileNameProvider implements FileNameProvider {

    @Override
    public Path path() {
        return Paths.get(SmartBddConfig.getBaseFolder(), SmartBddConfig.getDataFolder());
    }

    @Override
    public Path indexFile() {
        return path().resolve("index.json");
    }

    @Override
    public Path file(TestSuite testSuite) {
        return outputFile(fullyQualifiedName(testSuite));
    }

    private String fullyQualifiedName(TestSuite testSuite) {
        return testSuite.getName();
    }

    private Path outputFile(String testName) {
        return path().resolve("TEST-" + testName + ".json");
    }
}
