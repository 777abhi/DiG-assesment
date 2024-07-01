package ui.base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import static ui.utilities.Constants.*;

public class BaseTest {

	public static WebDriver driver;

	public static FileReader filereader;
	public static Properties prop = new Properties();
	public static Properties locator = new Properties();
	String basePath = System.getProperty(USER_DIRECTORY);

	@BeforeTest
	public void setup() throws IOException {

		if (driver == null) {
			FileReader filereader = new FileReader(
					basePath + File.separator + "src" + File.separator + "test" + File.separator + "resources"
							+ File.separator + "configFiles" + File.separator + "config.properties");
			FileReader locatorfle = new FileReader(
					basePath + File.separator + "src" + File.separator + "test" + File.separator + "resources"
							+ File.separator + "configFiles" + File.separator + "locator.properties");
			prop.load(filereader);
			locator.load(locatorfle);
		}
		if (prop.getProperty(BROWSER).equalsIgnoreCase(CHROME_BROWSER)) {

			System.setProperty("webdriver.chrome.driver", basePath + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "driver" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(prop.getProperty(TESTURL));
		} else if (prop.getProperty(BROWSER).equalsIgnoreCase(FIREFOX_BROWSER)) {
			WebDriverManager.firefoxdriver().create();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty(TESTURL));
		}

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		System.out.println("testDown successfully");
	}

	public void captureScreenShot(String fileName) {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(basePath + File.separator + SCREEN_SHOT_FOLDER + File.separator + fileName);
		try {
			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
