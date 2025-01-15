package be.howest.ti.ooansd.exam.util;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class config {
    private static final String CONFIG_PATH = "/config/config.properties";
    private static final Logger LOGGER = Logger.getLogger(config.class.getName());

    private static final config INSTANCE = new config();
    private final Properties properties = new Properties();

    private config() {
        try {
            properties.load(getClass().getResourceAsStream(CONFIG_PATH));
        }catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve config settings.", ex);
        }
    }
    public static config getInstance() {
        return INSTANCE;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
