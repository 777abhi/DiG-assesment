package ui.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	
	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir");
		System.out.println(path); 
		
		
		FileReader filereader = new FileReader( path+ "\\src\\test\\resources\\configFiles\\config.properties");
		Properties prop = new Properties();
		prop.load(filereader);
		System.out.println(prop.getProperty("browser"));
		System.out.println(prop.getProperty("testurl"));

	}
	

}
