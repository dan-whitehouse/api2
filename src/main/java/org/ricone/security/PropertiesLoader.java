package org.ricone.security;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.error.exception.ConfigException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static PropertiesLoader instance = null;
    private static final String PROPERTY_FILE = "security.properties";
    private static Resource resource = new ClassPathResource(PROPERTY_FILE);
    private static Properties properties = null;

    public static PropertiesLoader getInstance() {
        if(instance == null) {
            instance = new PropertiesLoader();

            try {
                properties = PropertiesLoaderUtils.loadProperties(resource);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getProperty(String key)  {
        String out = properties.getProperty(key);
        if(StringUtils.isNotEmpty(out)) {
            return out;
        }
        else {
            return null;
            //throw new ConfigException("Could not load key: " + key + " from: " + PROPERTY_FILE);
        }
    }

    public void setProperty(String key, String value) throws ConfigException {
        properties.setProperty(key, value);
    }
}
