package wordify;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLoader {
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public String loadFile(String fileName) {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            return toString(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toString(final InputStream stream) throws IOException {
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

    public static InputStreamReader inputStreamReader(InputStream stream) {
        return new InputStreamReader(stream, UTF8);
    }

    /**
     * Used scanner before another project
     * TODO test - does it work the same as above?
     */
    public String loadResourceAsStringWithDelimiter(String fileName) {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        String contents = scanner.useDelimiter("\\A").next();
        scanner.close();
        return contents;
    }

    /**
     * This appears to strip off line breaks, thanks
     * TODO test - does it work the same as above?
     */
    public String loadFileJava8Style(String fileName) {
        Path path;
        Stream<String> lines = null;
        String contents = null;
        try {
            URI uri = getClass().getClassLoader().getResource(fileName).toURI();
            path = Paths.get(uri);
            lines = Files.lines(path);
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
}
