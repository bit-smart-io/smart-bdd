package io.bitsmart.bdd.report.report;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileUtils {

    /** for src/main/resources/myfolder use the string myfolder */
    public Path pathForProjectResource(String folder) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(folder);
        if (resource == null) {
            // maybe an error file not found?
            return null;
        }
        return Paths.get(resource.toURI());
    }

    public  void copyFolder(Path src, Path dest) throws IOException {
        try (Stream<Path> stream = Files.walk(src)) {
            stream.forEach(source -> copyFile(source, dest.resolve(src.relativize(source))));
        }
    }

    /** source and dest have to be a file (not a directory) else it throws Caused by: java.nio.file.DirectoryNotEmptyException */
    public void copyFile(Path source, Path dest) {
        try {
            System.out.println("source: " + source + ", dest: " + dest);
            if (source.toFile().isFile())
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            //throw new RuntimeException(e.getMessage(), e);
        }
    }
}
