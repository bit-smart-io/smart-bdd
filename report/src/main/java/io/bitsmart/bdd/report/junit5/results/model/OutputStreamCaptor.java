package io.bitsmart.bdd.report.junit5.results.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputStreamCaptor {
    private final PrintStream standardOut = System.out;
    private final PrintStream standardErr = System.err;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();

    public OutputStreamCaptor() {
    }

    public void start() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errorStreamCaptor));
    }

    public void stop() {
        System.setOut(standardOut);
        System.setOut(standardErr);
    }

    public String getSystemOut() {
        return outputStreamCaptor.toString();
    }

    public String getSystemErr() {
        return errorStreamCaptor.toString();
    }
}
