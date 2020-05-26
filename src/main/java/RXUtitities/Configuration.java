package RXUtitities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
	public static Logger logger = LogManager.getLogger(Configuration.class.getName());
	private String propertiesFileName;
	private static Properties properties;
	//public static String filePath = new File("").getAbsolutePath();

	public Configuration() {
		//this.propertiesFileName = propertiesFileName;
		properties = new Properties();

		try {
			String filePath=System.getProperty("user.dir")+"/resources/RX.properties";
			
			FileInputStream fis = new FileInputStream(filePath);
			properties.load(fis);
			logger.info("Properties file '" + propertiesFileName + "' loaded.");
		} catch (IOException e) {
			logger.error("Properties file '" + propertiesFileName + "' not loaded.");
		}
	}

	public  String getProperty(String propertyName) {
		String ret = "";
		logger.info("Looking for property '" + propertyName + "'...");
		ret = properties.getProperty(propertyName);
		logger.info("Property value is '" + ret + "'");
		return ret;
	}

	public void putProperty(String propertyName, String propertyValue) {
		properties.put(propertyName, propertyValue);
	}

}



