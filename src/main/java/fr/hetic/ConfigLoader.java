package fr.hetic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties configProps = new Properties();

    public ConfigLoader() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Unable to find application.properties");
                return;
            }

            configProps.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return configProps.getProperty(key);
    }
}