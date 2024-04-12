package fr.hetic;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            showCorrectUsage();
            return;
        }

        ConfigLoader config = new ConfigLoader();
        String implementation = config.getProperty("implementation").toUpperCase();

        switch (implementation) {
            case "JDBC":
                String dbUrl = config.getProperty("db.url");
                String username = config.getProperty("db.user");
                String password = config.getProperty("db.password");
                DataReader JdbcDataReader = new JdbcDataReader(dbUrl, username, password);
                processOperations(JdbcDataReader);
                break;
            case "FILE":
                if (args.length != 2) {
                    System.out.println("File processing requires the directory path as an argument.");
                    showCorrectUsage();
                    return;
                }
                FileProcessor fileProcessor = new FileProcessor();
                DirectoryProcessor directoryProcessor = new DirectoryProcessor(fileProcessor);
                directoryProcessor.processDirectory(args[0]);

                break;
            default:
                showCorrectUsage();
                break;
        }
    }

    private static void processOperations(DataReader dataReader) {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processOperations(dataReader);
    }

    private static void showCorrectUsage() {
        System.out.println("Incorrect usage. Please check the implementation type in application.properties:");
        System.out.println("For JDBC implementation: JDBC");
        System.out.println("For JDBC implementation: java -cp fr.hetic.Main");
        System.out.println("For FILE implementation: FILE");
        System.out.println("For FILE implementation: java -cp fr.hetic.Main <directory_path>");
    }

}