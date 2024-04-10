package fr.hetic;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class DirectoryProcessor {
    private final FileProcessor fileProcessor;

    public DirectoryProcessor(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public void processDirectory(String directoryPath) {
        Path dirPath = Paths.get(directoryPath);
        try (Stream<Path> paths = Files.walk(dirPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".op"))
                    .forEach(fileProcessor::processFile);
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }
}