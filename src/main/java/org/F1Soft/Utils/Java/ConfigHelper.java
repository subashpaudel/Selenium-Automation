package org.F1Soft.Utils.Java;

import java.io.*;
import java.util.Properties;

public class ConfigHelper {
    InputStream inputStream;
    Properties prop = new Properties();
    String propFileName = "config.properties";

    public ConfigHelper() {
        try {

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file " + propFileName + "not found in the classpath");
            }

        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {

            }
        }
    }

    public String getProperty(String property) {
        return prop.getProperty(property);
    }

    public void setProperty(String property, String value) {
        try (OutputStream output = new FileOutputStream(getClass().getClassLoader().getResourceAsStream(propFileName).toString())) {
            prop.setProperty(property, value);
            prop.store(output, null);
            System.out.println(prop);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
