package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.report.model.TestSuite;

import java.nio.file.Path;

public interface FileNameProvider {

    Path path();

    Path indexFile();

    Path file(TestSuite testSuite);
}
