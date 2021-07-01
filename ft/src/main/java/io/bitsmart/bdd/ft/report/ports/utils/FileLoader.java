package io.bitsmart.bdd.ft.report.ports.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileLoader {
    public static final Charset UTF8 = StandardCharsets.UTF_8;

    public String toString(String fileName) {
        try (InputStream resourceAsStream = inputStream(fileName)) {
            return toString(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String toString(File file) throws IOException {
        FileReader reader = new FileReader(file);
        return toString(reader);
    }

    private static String toString(final InputStream stream) throws IOException {
        return toString(inputStreamReader(stream));
    }

    public static String toString(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[512];
        int read = reader.read(buffer);
        while (read > 0) {
            builder.append(buffer, 0, read);
            read = reader.read(buffer);
        }
        return builder.toString();
    }

    public InputStream inputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

    public static InputStreamReader inputStreamReader(InputStream stream) {
        return new InputStreamReader(stream, UTF8);
    }
}
