package fr.hetic;

public class ErrorHandler {
    public void handleException(Exception e) {
        System.err.println("Error: " + e.getMessage());
        showCorrectUsage();
    }

    private static void showCorrectUsage() {
        System.out.println("Incorrect usage. Please check the implementation type in application.properties:");
        System.out.println("For JDBC implementation: JDBC");
        System.out.println("For JDBC implementation: java -cp fr.hetic.Main");
        System.out.println("For FILE implementation: FILE");
        System.out.println("For FILE implementation: java -cp fr.hetic.Main <directory_path>");
    }
}
