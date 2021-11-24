package com.java.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library {
	public static WebDriver driver;
	public Properties ObjProperty = new Properties();
	
	public static void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		//library obj = new library();
		//WebDriverWait wait = new WebDriverWait(obj.driver, 60);// 60 seconds
		
		WebDriverWait wait = new WebDriverWait(driver, constants.timeout);// 60 seconds
		wait.until(pageLoadCondition);
	}
	
	public void launchBrowser() {
		String browser = ObjProperty.getProperty("browser");
		switch (browser) {
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
			// driver = new HtmlUnitDriver;
		}
		driver.get(ObjProperty.getProperty("GmoOnloneURL_SIT"));
		driver.manage().window().maximize();
		// note : implicit wait : This is a global waiting mechanism which is
		// applicable
		// for all web Elements through out the drive instance. It will wait
		// until driver is able
		// to find the locator type and it will process if it is able to
		// identify the element locator
		driver.manage().timeouts().implicitlyWait(constants.timeout30Sec, TimeUnit.SECONDS);

	}

	public void readpropertyfile() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		File Fileobj = new File(System.getProperty("user.dir") + "//src//test//resources//config.properties");
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
	
	public static WebElement FindElement(String Orep){
		String locatorValue = Orep;
		System.out.println(locatorValue);
		String locator = locatorValue.split("&")[0];
		String value = locatorValue.split("&")[1];
		System.out.println("locator: "+locator);
		System.out.println("value: "+value);
		
		By search = null;
		if(locator.equalsIgnoreCase("id")){
			search = By.id(value);
		}else if(locator.equalsIgnoreCase("name")){
			search = By.name(value);
		}else if(locator.equalsIgnoreCase("classname")){
			search = By.className(value);
		}else if(locator.equalsIgnoreCase("tagname")){
			search = By.tagName(value);
		}else if(locator.equalsIgnoreCase("xpath")){
			search = By.xpath(value);
		}else if(locator.equalsIgnoreCase("css")){
			search = By.cssSelector(value);
		}else if(locator.equalsIgnoreCase("linkText")){
			search = By.linkText(value);
		}else if(locator.equalsIgnoreCase("partiallinkText")){
			search = By.partialLinkText(value);
		}
		return driver.findElement(search);
		
	}
}
