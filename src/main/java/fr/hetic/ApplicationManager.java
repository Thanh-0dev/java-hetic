package fr.hetic;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.nio.file.Path;

public class ApplicationManager {
    private final ApplicationContextConfigurator configurator;

    public ApplicationManager() {
        this.configurator = new ApplicationContextConfigurator();
    }

    public void start() {
        try (ClassPathXmlApplicationContext context = configurator.createContext()) {
            processDataReader(context);
        } catch (Exception e) {
            ErrorHandler errorHandler = new ErrorHandler();
            errorHandler.handleException(e);
        }
    }

    private void processDataReader(ClassPathXmlApplicationContext context) {
        DataReader dataReader = context.getBean("reader", DataReader.class);
        FileProcessor fileProcessor = new FileProcessor();

        if (dataReader instanceof FilesystemDataReader) {
            processFilesystemData((FilesystemDataReader) dataReader, fileProcessor);
        } else if (dataReader instanceof JdbcDataReader) {
            fileProcessor.processOperations(dataReader);
        }
    }

    private void processFilesystemData(FilesystemDataReader dataReader, FileProcessor fileProcessor) {
        Path directoryPath = dataReader.getDirectoryPath();
        DirectoryProcessor directoryProcessor = new DirectoryProcessor(fileProcessor);
        directoryProcessor.processDirectory(directoryPath);
    }
}
