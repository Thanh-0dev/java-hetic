package fr.hetic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationContextConfigurator {
    private final Properties configProps = new Properties();

    public ApplicationContextConfigurator() {
        loadProperties();
    }

    public ClassPathXmlApplicationContext createContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        String profile = determineProfile();
        context.getEnvironment().setActiveProfiles(profile);
        context.setConfigLocation("beans.xml");
        context.refresh();
        return context;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Unable to find application.properties");
                return;
            }
            configProps.load(input);
        } catch (IOException e) {
            System.err.println("Failed to load application properties.");
            e.printStackTrace();
        }
    }

    private String determineProfile() {
        String implementationType = getProperty("implementation").toUpperCase();
        return "JDBC".equals(implementationType) ? "database" : "filesystem";
    }

    public String getProperty(String key) {
        return configProps.getProperty(key);
    }
}
