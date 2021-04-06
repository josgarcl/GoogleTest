package testObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.Base;

public class GoogleResultsPage extends Base {
	
	private static WebElement element = null;
	//private static WebDriver driver = null;
	
	/*public GoogleResultsPage(WebDriver driver) {
		this.driver = driver;
	}*/

	//Return list of results (class "g" is common to all results)
	public List<WebElement> link_results() {
		List<WebElement> list = driver.findElements(By.className("g"));
		
		return list;
	}
	
	//Extra method to return a specific result
	public WebElement returnResultNum(int numResult) {
		List<WebElement> list = link_results();
		element = list.get(numResult-1).findElement(By.tagName("a"));
		
		return element;
	}
	
	public WebElement returnResultText(String text) {
		return driver.findElement(By.partialLinkText(text)).findElement(By.tagName("a"));
	}
	
}
