package fr.hetic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Calculator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java OperationFileCalculator <path>");
            return;
        }

        Path operationPath = Paths.get(args[0]);
        try (DirectoryStream<Path> flux = Files.newDirectoryStream(operationPath, "*.op")) {
            for (Path filePath : flux) {
                treatFile(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error when treating repository: " + e.getMessage());
        }
    }

    private static void treatFile(Path entryFilePath) {
        Path exitFilePath = Paths.get(entryFilePath.toString().replaceAll("\\.op$", ".res"));
        try (BufferedReader reader = Files.newBufferedReader(entryFilePath);
             BufferedWriter writer = Files.newBufferedWriter(exitFilePath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String result = calculate(line);
                writer.write(result);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error when treating file: " + e.getMessage());
        }
    }

    private static String calculate(String entryLine) {
        String[] operationArgs = entryLine.split(" ");
        if (operationArgs.length != 3) {
            return "ERROR";
        }

        try {
            Integer num1 = Integer.parseInt(operationArgs[0]);
            Integer num2 = Integer.parseInt(operationArgs[1]);
            String operator = operationArgs[2];

            return switch (operator) {
                case "+" -> String.valueOf(num1 + num2);
                case "-" -> String.valueOf(num1 - num2);
                case "*" -> String.valueOf(num1 * num2);
                default -> "ERROR";
            };
        } catch (NumberFormatException e) {
            return "ERROR";
        }
    }
}
