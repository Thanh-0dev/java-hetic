package fr.hetic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileProcessor {
    public void processFile(Path filePath) {
        Path outputPath = Paths.get(filePath.toString().replaceAll("\\.op$", ".res"));
        try (Stream<String> lines = Files.lines(filePath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            lines.map(line -> line.split(" "))
                    .filter(parts -> parts.length == 3)
                    .forEach(parts -> {
                        performOperationToFile(parts, writer);
                    });
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private static boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void performOperationToFile(String[] parts, BufferedWriter writer) {
        try {
            if (!isValidNumber(parts[0]) || !isValidNumber(parts[1])) {
                writer.write("ERROR");
                writer.newLine();
                return;
            }

            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            String operator = parts[2];
            OperationStrategy strategy = OperationStrategyFactory.getStrategy(operator);

            writer.write((strategy != null) ? strategy.execute(num1, num2) : "ERROR");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}
