package tests;

import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import selenium.Base;

public class GoogleSearchTestDirectorsCut {
	
	static WebDriver driver = null; //Browser driver
	static WebDriverWait wait = null;

	static ExtentReports extent = null;
	static ExtentSparkReporter spark = null;
	static ExtentTest myTest = null;
	
	@BeforeSuite
	public static void startProcess() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("report.html");
		extent.attachReporter(spark);
		
		myTest = extent.createTest("Google Test - Director's Cut");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = Base.startUp();
		myTest.log(Status.PASS, "Chrome has been opened");
	}
	
	@Test
	public static void simpleSearchTest() {
		
		wait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.google.com");
		
		//Check if google is loaded
		Assert.assertTrue(driver.getTitle().contains("Google"));
		myTest.log(Status.PASS, "We are inside Google");
		
		//Wait till iframe is loaded
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe")));
		
		driver.switchTo().frame(0);
		
		//Click on "acepto"
		driver.findElement(By.xpath("//div[@id='introAgreeButton']")).click();
		myTest.log(Status.PASS, "iFrame out!");
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.name("q")).sendKeys("wandavision");
		
		//Wait until search button is clickable
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("btnK"))));
		
		driver.findElement(By.name("btnK")).click();
		
		//Class "g" is common to all results, so we wait until they're visible
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("g")));
		
		//Confirm we are in the results page
		Assert.assertTrue(driver.getTitle().contains("Buscar con Google"));
		myTest.log(Status.PASS, "We are in the results page");
		
		//Find a link with the text "filmaffinity"
		try {
			
			driver.findElement(By.partialLinkText("filmaffinity")).click();

			//Confirm we are inside FilmAffinity
			Assert.assertTrue(driver.getTitle().contains("FilmAffinity"));
			myTest.log(Status.PASS, "We just entered FilmAffinity");
			
		} catch (Exception e) {
			
			Assert.fail("Link not found!");
			myTest.log(Status.WARNING, "Link not found!");
			
		}
		
		//Close browser
		driver.close();
		myTest.log(Status.PASS, "Chrome Closed");
		
		//Create report
		extent.flush();
		
	}

}
