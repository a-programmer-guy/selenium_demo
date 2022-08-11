package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath = 
			"../configurations/Configuration.properties";
	
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at: " + propertyFilePath);
		} 
	}
	
	public String getDriverPath() {
		String driverPath = properties.getProperty("chromePath");
		if(driverPath!=null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file");
	}

}
