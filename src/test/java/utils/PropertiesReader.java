package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader{
	
	private Properties properties;
	public String configFile = System.getProperty("user.dir") + "/src/test/resources/config/globalParameters.properties";
	
	public String getPropertyValue(String propertyName) {

		FileInputStream inputStream = null;
		properties = new Properties();
		String propertyValue = null;
		try {
			inputStream = new FileInputStream(configFile);
			properties.load(inputStream);
			propertyValue = properties.getProperty(propertyName);
		}
		catch (Exception e) {
			DriverManager.logger.error("Error while fetching property value. Property Name = " + propertyName);
			e.printStackTrace();
		}
		return propertyValue;
	}
	
	
}
