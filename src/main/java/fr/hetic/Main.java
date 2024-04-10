package fr.hetic;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java fr.hetic.Main <directory_path>");
            return;
        }

        FileProcessor fileProcessor = new FileProcessor();
        DirectoryProcessor directoryProcessor = new DirectoryProcessor(fileProcessor);
        directoryProcessor.processDirectory(args[0]);
    }
}