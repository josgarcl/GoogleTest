package testObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.Base;

public class GoogleSearchPage extends Base {
	
	private static WebElement element = null;
	//private static WebDriver driver = null;
	
	/*public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
	}*/

	//Search textbox element
	public WebElement textbox_searchBox() {
		element = driver.findElement(By.name("q"));
		return element;
	}
	
	//Search button element
	public WebElement button_searchButton() {
		element = driver.findElement(By.name("btnK"));
		return element;
	}
	
	//iFrame closing button
	public WebElement button_accept() {
		element = driver.findElement(By.xpath("//div[@id='introAgreeButton']"));
		return element;
	}
	
}
