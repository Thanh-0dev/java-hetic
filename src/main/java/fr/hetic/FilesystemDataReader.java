package fr.hetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class FilesystemDataReader implements DataReader {

    private final Path directoryPath;

    public FilesystemDataReader(String directoryPath) {
        this.directoryPath = Paths.get(directoryPath);
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    @Override
    public Map<String, List<String>> readData() throws IOException {
        Map<String, List<String>> operations = new HashMap<>();
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".op"))
                    .forEach(path -> {
                        List<String> lines = new ArrayList<>();
                        try (BufferedReader reader = Files.newBufferedReader(path)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                lines.add(line);
                            }
                            operations.put(path.toString(), lines);
                        } catch (IOException e) {
                            System.err.println("Failed to read file: " + path);
                        }
                    });
        }
        return operations;
    }
}
