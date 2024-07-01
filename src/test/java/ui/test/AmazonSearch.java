package ui.test;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ui.utilities.LocatorConstant.*;
import static ui.utilities.Constants.*;

import ui.base.BaseTest;

public class AmazonSearch extends BaseTest {
	
	@Test(dataProvider = "testdata")
	public void uiTestSearchBoxofAmazon(String serachItem, String searchResult) {
		SoftAssert softassert = new SoftAssert();
		driver.manage().window().maximize();
		driver.findElement(By.id(locator.getProperty(SEARCH_BOX))).sendKeys(serachItem);
		driver.findElement(By.id(locator.getProperty(SUBMIT_BUTTON))).click();
		String value = driver.findElement(By.xpath(locator.getProperty(SEARCH_ELEMENT))).getText();
		softassert.assertTrue(value.contains(searchResult), SEARCH_DATA_NOT_MATCHED);
	}

	
	@DataProvider(name = "testdata")
	public Object[][] testData() {
		return new Object[][] {
			{"iphone", "Apple iPhone"}
		};
		
	}
	
	
}
