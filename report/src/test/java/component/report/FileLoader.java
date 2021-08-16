package component.report;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

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

    public String toStringUsingScanner(String fileName) {
        Scanner scanner = new Scanner(requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)));
        String contents = scanner.useDelimiter("\\A").next();
        scanner.close();
        return contents;
    }

    /**
     * This appears to strip off line breaks, thanks
     */
    public String toStringJava8Style(String fileName) {
        Path path;
        Stream<String> lines = null;
        String contents = null;
        try {
            URI uri = requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI();
            path = Paths.get(uri);
            lines = Files.lines(path);
            // TODO here I think that we you'd need to add a line break
            contents = lines.collect(Collectors.joining());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
        return contents;
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
