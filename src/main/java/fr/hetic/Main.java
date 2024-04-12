package fr.hetic;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            showCorrectUsage();
            return;
        }

        String dataSourceType = args[0].toUpperCase();
        ConfigLoader config = new ConfigLoader();

        switch (dataSourceType) {
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
        System.out.println("Incorrect usage. Please specify the data source type and relevant parameters:");
        System.out.println("For file processing: java -cp fr.hetic.Main FILE <directory_path>");
        System.out.println("For JDBC: java -cp fr.hetic.Main JDBC");
    }

}