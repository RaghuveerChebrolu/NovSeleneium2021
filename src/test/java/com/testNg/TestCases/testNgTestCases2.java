package com.testNg.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		waitForPageToLoad();
		String GMOtitle = driver.getTitle();
		System.out.println(GMOtitle);
		Assert.assertEquals(GMOtitle, "Welcome to Green Mountain Outpost");

	}

	@Test(priority = 1, dependsOnMethods = { "ValidateGMOOnLineLoadedSuccessfully" })
	public void ValidateEnterGmoOnLine() throws InterruptedException {
		System.out.println("inside ValidateEnterGmoOnLine");
		driver.findElement(By.name("bSubmit")).click();
		waitForPageToLoad();
		driver.findElement(By.name("QTY_BACKPACKS")).clear();
		driver.findElement(By.name("QTY_BACKPACKS")).sendKeys("4");
		driver.findElement(By.name("QTY_BACKPACKS")).clear();
		driver.findElement(By.name("QTY_BACKPACKS")).sendKeys("6");
		// driver.close();
		/*
		 * Thread.sleep(5000); driver.navigate().back(); Thread.sleep(5000);
		 * driver.navigate().forward(); driver.navigate().refresh();
		 */
		driver.findElement(By.name("bSubmit")).click();
	}

	@Test(priority = 2, dependsOnMethods = { "ValidateEnterGmoOnLine" })
	public void ValidatePlaceOrderPage() {
		waitForPageToLoad();
		String Title = driver.findElement(By.xpath("//h1[contains(text(),'Place Order')]")).getText();
		System.out.println("Title: " + Title);
		Assert.assertEquals(Title, "Place Order");
		String UnitPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		System.out.println(UnitPrice);
		String FloatValue = UnitPrice.substring(2).trim();
		System.out.println("FloatValue: " + FloatValue);
		// int UNiPriceintergervalue = Integer.parseInt(FloatValue);
		// System.out.println(UNiPriceintergervalue);
		float calculatedFloatValue = Float.parseFloat(FloatValue) * 6;
		System.out.println(calculatedFloatValue);
		String TotalPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText();
		String TotalPricefromApplication = TotalPrice.substring(2).trim();
		float FloatValuefromApplication = Float.parseFloat(TotalPricefromApplication);
		System.out.println("FloatValuefromApplication: " + FloatValuefromApplication);
		Assert.assertEquals(calculatedFloatValue, FloatValuefromApplication);
	}

	@Test(priority = 3)
	public void ValidatingAlerts() {
		System.out.println("inside ValidatingAlerts");
		driver.navigate().to(ObjProperty.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert ObjalertbuttonT = driver.switchTo().alert();
		String alertbuttonText = ObjalertbuttonT.getText();
		System.out.println("alertbuttonText: " + alertbuttonText);
		Assert.assertEquals(alertbuttonText, "You clicked a button");
		ObjalertbuttonT.accept();

		driver.findElement(By.id("timerAlertButton")).click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert ObjectTimerAlert = driver.switchTo().alert();
		String TimerAlertText = ObjectTimerAlert.getText();
		System.out.println("TimerAlertText: " + TimerAlertText);
		// Assert.assertEquals(TimerAlertText, "This alert appeared after 5
		// second");

		SoftAssert objSF = new SoftAssert();
		objSF.assertEquals(TimerAlertText, "This alert appeared after 5 second");
		ObjalertbuttonT.accept();

		driver.findElement(By.id("confirmButton")).click();
		Alert ObjectconfirmButton = driver.switchTo().alert();
		String confirmButtonAlertText = ObjectconfirmButton.getText();
		System.out.println("confirmButtonAlertText: " + confirmButtonAlertText);
		Assert.assertEquals(confirmButtonAlertText, "Do you confirm action?");
		ObjalertbuttonT.dismiss();
		// span[@id='confirmResult']
		String confirmbuttontext = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
		Assert.assertEquals(confirmbuttontext, "You selected Cancel");

		driver.findElement(By.id("promtButton")).click();
		Alert ObjectpromtButton = driver.switchTo().alert();
		ObjectpromtButton.sendKeys("How are you");
		ObjalertbuttonT.accept();
		String promptResultext = driver.findElement(By.id("promptResult")).getText();
		Assert.assertEquals(promptResultext, "You entered How are you");
		objSF.assertAll();
	}

	@Test(priority = 4)
	public void ValditingHAndlingOfFrames() {
		System.out.println("inside ValditingHAndlingOfFrames");
		driver.navigate().to(ObjProperty.getProperty("FramesURL"));
		waitForPageToLoad();
		driver.switchTo().frame("singleframe");

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("inside single frame");

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[contains(text(),'in an Iframe')]")).click();

		WebElement multipleframe = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(multipleframe);

		WebElement singleframe = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(singleframe);

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("single frame with in multi frame");
		driver.switchTo().defaultContent();
	}

	@Test(priority = 5)
	public void HandlingOfWindows() {
		System.out.println("inside HandlingOfWindows");
		driver.navigate().to(ObjProperty.getProperty("WindowsURL"));
		waitForPageToLoad();
		String ParentWindow = driver.getWindowHandle();
		Set<String> Allwindows = driver.getWindowHandles();
		for (String Individualwindow : Allwindows) {
			driver.switchTo().window(Individualwindow);
			String title = driver.getTitle();
			System.out.println(title);
			if (title.equals("Tech Mahindra")) {
				String url = driver.getCurrentUrl();
				System.out.println("url:" + url);
			}
			else if (title.equals("ICICI")) {
				driver.close();
			} else if (title.contains("Job Search")) {
				String pagesource = driver.getPageSource();
				System.out.println("---------------");
				//System.out.println(pagesource);
				System.out.println("---------------");
			} else if (title.contains("Tech")) {
				driver.manage().window().maximize();
			} 
			
		}
		driver.switchTo().window(ParentWindow);
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
		/*
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 * 
		 * WebDriverManager.edgedriver().setup(); driver=new EdgeDriver();
		 * 
		 * driver.get("http://demo.borland.com/gmopost/");
		 * driver.manage().window().maximize(); //note : implicit wait : This is
		 * a global waiting mechanism which is applicable //for all web Elements
		 * through out the drive instance. It will wait until driver is able
		 * //to find the locator type and it will process if it is able to
		 * identify the element locator
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

	// Helper Methods
	private void readpropertyfile() throws IOException {
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

	private void launchBrowser() {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 60);// 60 seconds
		wait.until(pageLoadCondition);
	}
}
