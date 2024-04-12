package fr.hetic;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class DirectoryProcessor {
    private final FileProcessor fileProcessor;

    public DirectoryProcessor(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public void processDirectory(Path directoryPath) {
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".op"))
                    .forEach(fileProcessor::processFile);
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }
}