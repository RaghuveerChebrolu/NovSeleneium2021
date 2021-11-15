package com.testNg.TestCases;

import org.testng.annotations.Test;

import com.google.inject.spi.Elements;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgTestCases2 {
	WebDriver driver;
	Properties ObjProperty = new Properties();

	@Test(priority = 0)
	public void ValidateGMOOnLineLoadedSuccessfully() {
		System.out.println("inside testcase1");
		String GMOtitle = driver.getTitle();
		System.out.println(GMOtitle);
		Assert.assertEquals(GMOtitle, "Welcome to Green Mountain Outpost");
		
	}

	@Test(priority = 1, dependsOnMethods = { "ValidateGMOOnLineLoadedSuccessfully" })
	public void ValidateEnterGmoOnLine() throws InterruptedException {
		System.out.println("inside ValidateEnterGmoOnLine");
		driver.findElement(By.name("bSubmit")).click();
		driver.findElement(By.name("QTY_BACKPACKS")).clear();
		driver.findElement(By.name("QTY_BACKPACKS")).sendKeys("4");
		driver.findElement(By.name("QTY_BACKPACKS")).clear();
		driver.findElement(By.name("QTY_BACKPACKS")).sendKeys("6");
		//driver.close();
		/*Thread.sleep(5000);
		driver.navigate().back();
		Thread.sleep(5000);
		driver.navigate().forward();
		driver.navigate().refresh();*/
		driver.findElement(By.name("bSubmit")).click();
	}
	
	@Test(priority = 2,dependsOnMethods = { "ValidateEnterGmoOnLine" })
	public void ValidatePlaceOrderPage(){
		String Title = driver.findElement(By.xpath("//h1[contains(text(),'Place Order')]")).getText();
		System.out.println("Title: "+Title);
		Assert.assertEquals(Title, "Place Order");
		String UnitPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		System.out.println(UnitPrice);
		String FloatValue = UnitPrice.substring(2).trim();
		System.out.println("FloatValue: "+FloatValue);
		//int UNiPriceintergervalue = Integer.parseInt(FloatValue);
		//System.out.println(UNiPriceintergervalue);
		float calculatedFloatValue = Float.parseFloat(FloatValue)*6;
		System.out.println(calculatedFloatValue);
		String TotalPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText();
		String TotalPricefromApplication = TotalPrice.substring(2).trim();
		float FloatValuefromApplication = Float.parseFloat(TotalPricefromApplication);
		System.out.println("FloatValuefromApplication: "+FloatValuefromApplication);
		Assert.assertEquals(calculatedFloatValue, FloatValuefromApplication);
	}
	
	
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		launchBrowser();
	}

	

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
	/*	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		  WebDriverManager.edgedriver().setup();
		  driver=new EdgeDriver();
		 
		driver.get("http://demo.borland.com/gmopost/");
		driver.manage().window().maximize();
		//note : implicit wait : This is a global waiting mechanism which is applicable
		//for all web Elements through out the drive instance. It will wait until driver is able
		//to find the locator type and it will process if it is able to identify the element locator 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
*/
		try {
			readpropertyfile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}
	
	//Helper Methods
	private void readpropertyfile() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		File Fileobj = new File(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		try {
			FileInputStream Fileinput = new FileInputStream(Fileobj);
			
			ObjProperty.load(Fileinput);
			ObjProperty.getProperty("browser");
			ObjProperty.getProperty("GmoOnloneURL_SIT");
			System.out.println(ObjProperty.getProperty("browser"));
			System.out.println(ObjProperty.getProperty("GmoOnloneURL_SIT"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void launchBrowser() {
		String browser = ObjProperty.getProperty("browser");
		switch(browser){
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver(); 
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(objChromeOptions);
			DesiredCapabilities ObjDesiredCap = DesiredCapabilities.chrome();
			ObjDesiredCap.setCapability(ChromeOptions.CAPABILITY, objChromeOptions);
			ObjDesiredCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		default:
			//driver = new HtmlUnitDriver;
		}
		driver.get(ObjProperty.getProperty("GmoOnloneURL_SIT"));
		driver.manage().window().maximize();
		//note : implicit wait : This is a global waiting mechanism which is applicable
		//for all web Elements through out the drive instance. It will wait until driver is able
		//to find the locator type and it will process if it is able to identify the element locator 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
}
