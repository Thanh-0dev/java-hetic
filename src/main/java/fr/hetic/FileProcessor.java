package fr.hetic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.stream.Stream;
import java.util.List;

public class FileProcessor {
    public void processOperations(DataReader dataReader) {
        try {
            Map<String, List<String>> operations = dataReader.readData();

            for (Map.Entry<String, List<String>> entry : operations.entrySet()) {
                String key = entry.getKey();
                List<String> operationList = entry.getValue();
                Path outputPath = Path.of("./" + key + ".res");

                try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                    operationList.stream()
                            .map(line -> line.split(" "))
                            .filter(parts -> parts.length == 3)
                            .forEach(parts -> {
                                performOperationToFile(parts, writer);
                            });
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing operations: " + e.getMessage());
        }
    }

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