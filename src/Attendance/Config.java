package Attendance;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
