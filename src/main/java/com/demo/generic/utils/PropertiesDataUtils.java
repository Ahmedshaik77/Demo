package com.demo.generic.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;

public class PropertiesDataUtils {

    private Properties properties = null;
    InputStream is = null;
    private static PropertiesDataUtils propertiesDataUtils = null;
    public static LinkedHashMap<String, String> configDataStore = new LinkedHashMap<String, String>();

    private PropertiesDataUtils(String filePath) {
        generateDataStore(filePath);
        PropertyConfigurator.configure(System.getProperty("user.dir")+"//src//test//resources//config//log4j_local.properties");
    }

    private PropertiesDataUtils() {
    	
    }
    
    private String getPropertyValue(String key) {
        return this.properties.getProperty(key);
    }
    private void setPropertyValue(String key,String value) {
         this.properties.setProperty(key,value);
    }
   

    public static PropertiesDataUtils getInstance(String filePath) {
        if (propertiesDataUtils == null)
            propertiesDataUtils = new PropertiesDataUtils(filePath);
        return propertiesDataUtils;
    }


    private void generateDataStore(String filePath) {
        try {
            this.properties = new Properties();
            is=new FileInputStream(filePath);
            properties.load(is);
            Set<Object> keys = loadAllKeys();
            for (Object k : keys) {
                String key = (String) k;
                configDataStore.put(key, getPropertyValue(key));
            }

        } catch (FileNotFoundException fileNotFoundException) {
            String exceptionData = String.valueOf(fileNotFoundException.getCause().getMessage());

        } catch (IOException ioException) {
            String exceptionData = String.valueOf(ioException.getCause().getMessage());
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {
                    String exceptionData = String.valueOf(e.getCause().getMessage());

                }
            }

        }
    }


    private Set<Object> loadAllKeys() {
        Set<Object> keys = this.properties.keySet();
        return keys;
    }

  
    
}
