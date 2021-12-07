package com.java.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library {
	public static WebDriver driver;
	public static Properties ObjProperty = new Properties();
	/*
	 * ExtentHtmlReporter : responsible for look and feel of the report , we can
	 * specify the report name , document title , theme of the report
	 * 
	 * ExtentReports : used to create entries in your report , create test cases
	 * in report , who executed the test case, environment name , browser
	 * 
	 * ExtentTest : update pass fail and skips and logs the test cases results
	 */
	public static ExtentHtmlReporter HtmlReporter;
	public static ExtentReports ExtReports;
	public static ExtentTest ExtTest;

	public static void startExententReport() {
		// TODO Auto-generated method stub
		try {
			// specify location of the report
			HtmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "//test-output/ExtentReportV4.html");
			HtmlReporter.config().setDocumentTitle("Automation Report"); 
			//Title of the report
			HtmlReporter.config().setReportName("Functional and Regression Testing"); 
			// Name of the report
			HtmlReporter.config().setTheme(Theme.STANDARD);

			ExtReports = new ExtentReports();
			ExtReports.attachReporter(HtmlReporter);

			// Passing General information
			ExtReports.setSystemInfo("Host name", "localhost");
			ExtReports.setSystemInfo("Environemnt", "UAT");
			ExtReports.setSystemInfo("user", "QaTester1");
			ExtReports.setSystemInfo("Browser", ObjProperty.getProperty("browser"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		// library obj = new library();
		// WebDriverWait wait = new WebDriverWait(obj.driver, 60);// 60 seconds

		WebDriverWait wait = new WebDriverWait(driver, constants.timeout);// 60
																			// seconds
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
			/*
			 * WebDriverManager.chromedriver().setup(); ChromeOptions
			 * objChromeOptions = new ChromeOptions();
			 * objChromeOptions.setAcceptInsecureCerts(true); driver = new
			 * ChromeDriver(objChromeOptions); DesiredCapabilities ObjDesiredCap
			 * = DesiredCapabilities.chrome();
			 * ObjDesiredCap.setCapability(ChromeOptions.CAPABILITY,
			 * objChromeOptions);
			 * ObjDesiredCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
			 * true);
			 */
			WebDriverManager.chromedriver().setup();
			ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.setAcceptInsecureCerts(true);
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			objChromeOptions.setExperimentalOption("prefs", chromePrefs);
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

	public static WebElement FindElement(String Orep) {
		String locatorValue = Orep;
		System.out.println(locatorValue);
		String locator = locatorValue.split("&")[0];
		String value = locatorValue.split("&")[1];
		System.out.println("locator: " + locator);
		System.out.println("value: " + value);

		By search = null;
		if (locator.equalsIgnoreCase("id")) {
			search = By.id(value);
		} else if (locator.equalsIgnoreCase("name")) {
			search = By.name(value);
		} else if (locator.equalsIgnoreCase("classname")) {
			search = By.className(value);
		} else if (locator.equalsIgnoreCase("tagname")) {
			search = By.tagName(value);
		} else if (locator.equalsIgnoreCase("xpath")) {
			search = By.xpath(value);
		} else if (locator.equalsIgnoreCase("css")) {
			search = By.cssSelector(value);
		} else if (locator.equalsIgnoreCase("linkText")) {
			search = By.linkText(value);
		} else if (locator.equalsIgnoreCase("partiallinkText")) {
			search = By.partialLinkText(value);
		}
		return driver.findElement(search);

	}

	public static List<WebElement> FindElements(String Orep) {
		String locatorValue = Orep;
		System.out.println(locatorValue);
		String locator = locatorValue.split("&")[0];
		String value = locatorValue.split("&")[1];
		System.out.println("locator: " + locator);
		System.out.println("value: " + value);

		By search = null;
		if (locator.equalsIgnoreCase("id")) {
			search = By.id(value);
		} else if (locator.equalsIgnoreCase("name")) {
			search = By.name(value);
		} else if (locator.equalsIgnoreCase("classname")) {
			search = By.className(value);
		} else if (locator.equalsIgnoreCase("tagname")) {
			search = By.tagName(value);
		} else if (locator.equalsIgnoreCase("xpath")) {
			search = By.xpath(value);
		} else if (locator.equalsIgnoreCase("css")) {
			search = By.cssSelector(value);
		} else if (locator.equalsIgnoreCase("linkText")) {
			search = By.linkText(value);
		} else if (locator.equalsIgnoreCase("partiallinkText")) {
			search = By.partialLinkText(value);
		}
		return driver.findElements(search);

	}

	public static void verifyinglinks(String Url) throws Exception {
		try {
			URL obj = new URL(Url);
			HttpURLConnection objHttpConnection = (HttpURLConnection) obj.openConnection();
			objHttpConnection.connect();
			int ResponseCode = objHttpConnection.getResponseCode();
			if (ResponseCode >= 400 && ResponseCode < 600) {
				System.out.println(Url + ": " + "ResponseCode:" + ResponseCode + " is not a valid Link");
			} else if (ResponseCode >= 200 && ResponseCode < 400) {
				System.out.println(Url + ": " + "ResponseCode:" + ResponseCode + " is a valid Link");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String takescreeshot(WebDriver driver) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String dateName = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//screenshots//" + dateName
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	public static String takescreeshot(WebDriver driver,String testCaseName) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String dateName = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//screenshots//" + dateName +testCaseName
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
}
