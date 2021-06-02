package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Base {
	public Base() {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static Logger logger = Logger.getLogger("Shopping");
}
