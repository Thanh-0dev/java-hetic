package fr.hetic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

public class FileProcessor {
    public void processFile(Path filePath) {
        Path outputPath = Paths.get(filePath.toString().replaceAll("\\.op$", ".res"));
        try (BufferedReader reader = Files.newBufferedReader(filePath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = compute(line);
                if (result != null) {
                    writer.write(result);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private String compute(String inputLine) {
        String[] parts = inputLine.split(" ");
        if (parts.length != 3) {
            return "ERROR";
        }

        try {
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            String operator = parts[2];

            OperationStrategy strategy = OperationStrategyFactory.getStrategy(operator);
            return (strategy != null) ? strategy.execute(num1, num2) : "ERROR";
        } catch (NumberFormatException e) {
            return "ERROR";
        }
    }
}
