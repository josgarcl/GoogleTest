package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testObjects.GoogleResultsPage;
import testObjects.GoogleSearchPage;
import selenium.Base;

public class GoogleSearchTest {
	
	static WebDriver driver = null; //Browser driver
	static WebElement element = null; //Aux element

	public static void main(String[] args) {
		
		simpleSearchTest();
		
	}
	
	public static void simpleSearchTest() {
		
		//Start Chrome
		driver = Base.startUp();
		
		//We avoid having to call the static methods with the driver parameter each time
		GoogleSearchPage search = new GoogleSearchPage();
		GoogleResultsPage result = new GoogleResultsPage();
		
		//Go to Google
		driver.get("https://www.google.com");
		
		//Wait till iframe is loaded
		element = new WebDriverWait(driver, 10000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe")));
		
		//Switch to iframe (the only defining factor for this iFrame is its src)
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://consent.google.com?hl=es&origin=https://www.google.com&continue=https://www.google.com/&if=1&m=0&pc=s&wp=-1&gl=ES']")));
		
		//Click on "acepto"
		search.button_accept().click();
		
		//Go back to main
		driver.switchTo().defaultContent();
		
		//Write something in the textbox
		search.textbox_searchBox().sendKeys("wandavision");
		
		//Wait until search button is clickable
		element = new WebDriverWait(driver, 10000).until(ExpectedConditions.elementToBeClickable(search.button_searchButton()));
		
		//Click search button
		search.button_searchButton().click();
		
		//Class "g" is common to all results, so we wait until they're visible
		element = new WebDriverWait(driver, 10000).until(ExpectedConditions.presenceOfElementLocated(By.className("g")));
		
		result.returnResultNum(1).click();
		
		//Close browser
		//driver.close();
		
	}

}
