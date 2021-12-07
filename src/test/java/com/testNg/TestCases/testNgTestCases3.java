package com.testNg.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.google.inject.spi.Elements;
import com.java.Utility.ObjRepo;
import com.java.Utility.constants;
import com.java.Utility.library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgTestCases3 extends library {
	// WebDriver driver;
	// Properties ObjProperty = new Properties();

	@Test(priority = 0)
	public void ValidateGMOOnLineLoadedSuccessfully() {
		System.out.println("inside testcase1");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		waitForPageToLoad();
		String GMOtitle = driver.getTitle();
		System.out.println(GMOtitle);
		Assert.assertEquals(GMOtitle, "Welcome to Green Mountain Outpost");

	}

	@Test(priority = 1, dependsOnMethods = { "ValidateGMOOnLineLoadedSuccessfully" })
	
	public void ValidateEnterGmoOnLine() throws InterruptedException {
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		System.out.println("inside ValidateEnterGmoOnLine");
		driver.findElement(By.name("bSubmit")).click();
		waitForPageToLoad();
		// driver.findElement(By.name("QTY_BACKPACKS")).clear();
		// driver.findElement(By.name("QTY_BACKPACKS")).sendKeys("4");
		library.FindElement(ObjRepo.QTY_BACKPACKS).clear();
		// driver.findElement(By.name("QTY_BACKPACKS")).clear();
		// driver.findElement(By.name("QTY_BACKPACKS")).sendKeys(constants.FrameBackpackQty);
		library.FindElement(ObjRepo.QTY_BACKPACKS).sendKeys(constants.FrameBackpackQty);

		// driver.close();
		/*
		 * Thread.sleep(5000); driver.navigate().back(); Thread.sleep(5000);
		 * driver.navigate().forward(); driver.navigate().refresh();
		 */
		driver.findElement(By.name("bSubmit")).click();
	}

	@Test(priority = 2, dependsOnMethods = { "ValidateEnterGmoOnLine" })
	public void ValidatePlaceOrderPage() {
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
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
		float calculatedFloatValue = Float.parseFloat(FloatValue) * Integer.parseInt(constants.FrameBackpackQty);
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
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert ObjalertbuttonT = driver.switchTo().alert();
		String alertbuttonText = ObjalertbuttonT.getText();
		System.out.println("alertbuttonText: " + alertbuttonText);
		Assert.assertEquals(alertbuttonText, "You clicked a button");
		ObjalertbuttonT.accept();

		driver.findElement(By.id("timerAlertButton")).click();
		WebDriverWait wait = new WebDriverWait(driver, constants.timeout);
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
	public void ValditingHandlingOfFrames() throws InterruptedException {
		System.out.println("inside ValditingHAndlingOfFrames");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("FramesURL"));
		waitForPageToLoad();
		driver.switchTo().frame("singleframe");
		// ObjRepo obj = new ObjRepo();
		// driver.findElement(By.xpath(obj.SingleFrame)).sendKeys("inside single
		// frame");

		// driver.findElement(By.xpath(ObjRepo.SingleFrame1)).sendKeys("inside
		// single frame");

		library.FindElement(ObjRepo.FrameTextBox).sendKeys(constants.singleFrametextbox);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		// driver.findElement(By.xpath(ObjRepo.FrameWithInFrame)).click();
		library.FindElement(ObjRepo.FrameWithInFrame).click();

		// WebElement multipleframe =
		// driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		WebElement multipleframe = library.FindElement(ObjRepo.MuntipleFrames);
		driver.switchTo().frame(multipleframe);

		// WebElement singleframe =
		// driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		WebElement singleframe = library.FindElement(ObjRepo.Single_Frame);
		driver.switchTo().frame(singleframe);

		// driver.findElement(By.xpath("//input[@type='text']")).sendKeys("single
		// frame with in multi frame");
		library.FindElement(ObjRepo.FrameTextBox).sendKeys(constants.MultiFrametextbox);

		driver.switchTo().defaultContent();

	}

	@Test(priority = 5)
	public void HandlingOfWindows() {
		System.out.println("inside HandlingOfWindows");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
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
			} else if (title.equals("ICICI")) {
				driver.close();
			} else if (title.contains("Job Search")) {
				String pagesource = driver.getPageSource();
				System.out.println("---------------");
				// System.out.println(pagesource);
				System.out.println("---------------");
			} else if (title.contains("Tech")) {
				driver.manage().window().maximize();
			}

		}
		driver.switchTo().window(ParentWindow);
	}

	@Test(priority = 6)
	public void HanlingWebTable() {
		System.out.println("inside HanlingWebTable");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("WebTableURL"));
		waitForPageToLoad();
		String UserInput_LastName = constants.WebTableLastName;
		List<WebElement> AllNames = library.FindElements(ObjRepo.WebTableAllLastNames);
		System.out.println("AllNames: " + AllNames);
		for (int i = 0; i < AllNames.size(); i++) {
			String Script_LastName = AllNames.get(i).getText();
			System.out.println(Script_LastName);
			int row = i + 1;
			if (Script_LastName.equalsIgnoreCase("wagner")) {
				String Office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + row + "]/td[5]"))
						.getText();
				String StartDate = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + row + "]/td[6]"))
						.getText();
				String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + row + "]/td[7]"))
						.getText();
				String Position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + row + "]/td[4]"))
						.getText();
				String FistName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + row + "]/td[2]"))
						.getText();
				System.out.println("details of employee with " + UserInput_LastName + " :" + Office + " " + StartDate
						+ " " + Salary + " " + Position + " " + FistName);
				System.out.println("StartDate:" + StartDate);
				System.out.println("Salary:" + Salary);
				System.out.println("Position:" + Position);
				System.out.println("FistName:" + FistName);
				break;
			}
		}

	}

	@Test(priority = 7)
	public void MouseOperationRightClick() {
		System.out.println("inside MouseOperationRightClick");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("mouseOpeartionRightClick"));
		waitForPageToLoad();
		WebElement element = library.FindElement(ObjRepo.MouseOperationRightClick);
		Actions obj = new Actions(driver);
		obj.contextClick(element).build().perform();
		library.FindElement(ObjRepo.MouseOperationRCPaste).click();
		Alert MouseOperationAlert = driver.switchTo().alert();
		String AlertText = MouseOperationAlert.getText();
		Assert.assertEquals(AlertText, "clicked: paste");
		MouseOperationAlert.accept();
	}

	@Test(priority = 8)
	public void MouseOperationDoubleClick() throws InterruptedException {
		System.out.println("inside MouseOperationDoubleClick");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("mouseOpeartionDoubleClick"));
		waitForPageToLoad();
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		/*
		 * js.executeScript("window.scrollBy(0,500)");//to scroll down by 500
		 * pixels js.executeScript("window.scrollBy(0,-300)");//to scroll up by
		 * 300 pixels js.executeScript("window.scrollBy(500,0)");////to scroll
		 * right by 500 pixels
		 * js.executeScript("window.scrollBy(-400,0)");////to scroll left by 400
		 * pixels
		 */
		WebElement frameElement = library.FindElement(ObjRepo.MouseOperationframe);
		js.executeScript("arguments[0].scrollIntoView(true);", frameElement);
		driver.switchTo().frame(frameElement);

		WebElement targetDoubleclick = library.FindElement(ObjRepo.MouseOperationDoubleclickbox);
		Actions obj = new Actions(driver);
		obj.doubleClick(targetDoubleclick).build().perform();

		Color BackGroundColor = Color
				.fromString(library.FindElement(ObjRepo.MouseOperationDoubleclickbox).getCssValue("background-color"));
		System.out.println("BackGroundColor:" + BackGroundColor);
		String ActualBackGroundColor = BackGroundColor.asRgba();
		System.out.println("ActualBackGroundColor:" + ActualBackGroundColor);
		Assert.assertEquals(ActualBackGroundColor, "rgba(255, 255, 0, 1)");
		driver.switchTo().defaultContent();// to come back from frame to normal
											// page

	}

	@Test(priority = 9)
	public void MouseDragAndDrop() {
		System.out.println("inside MouseDragAndDrop");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("mouseOperationDragAndDrop"));
		waitForPageToLoad();
		WebElement element = library.FindElement(ObjRepo.MouseOperationframe);
		driver.switchTo().frame(element);
		Actions obj = new Actions(driver);
		WebElement source = library.FindElement(ObjRepo.MouseOperationSource);
		WebElement target = library.FindElement(ObjRepo.MouseOperationTarget);
		// obj.dragAndDrop(source, target).build().perform();
		obj.clickAndHold(source);
		obj.moveToElement(target);
		obj.release(target).build().perform();
		driver.switchTo().defaultContent();
	}

	// Below method is use validate the links avaiable in application res page.
	// Author : Raghu
	// This is test case
	@Test(priority = 10)
	public void validatingLinks() {
		System.out.println("inside validatingLinks");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("ValidatingLinks"));
		waitForPageToLoad();
		List<WebElement> AllLinks = library.FindElements(ObjRepo.links);
		int count = AllLinks.size();
		for (int i = 1; i < count; i++) {
			WebElement individuallink = AllLinks.get(i);
			String IndividualLinkUrl = individuallink.getAttribute("href");
			try {
				library.verifyinglinks(IndividualLinkUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test(priority=11)
	public void ValidatingFileUpload() throws AWTException, InterruptedException{
		System.out.println("inside ValidatingFileUpload");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("FileUpload"));
		waitForPageToLoad();
		Thread.sleep(10000);
		WebElement element = library.FindElement(ObjRepo.FileUpload1);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		//js.executeScript("arguments[0].click();", element);
		//element.click();
		Actions obj  = new Actions(driver);
		obj.click(element);
		StringSelection objStringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.jpg");
		Clipboard objClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objClipboard.setContents(objStringSelection, null);
		try {
			Transferable objTransferable = objClipboard.getContents(null);
			if (objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Robot objRobot = new Robot();
		objRobot.delay(250);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_CONTROL);
		objRobot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_V);
		objRobot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		
	}

	@Test(priority=12)
	public void ValidatingFileDownload() throws AWTException, InterruptedException{
		System.out.println("inside ValidatingFileDownload");
		ExtTest = ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperty.getProperty("FileDownload"));
		waitForPageToLoad();
		library.FindElement(ObjRepo.FileDownload100kb).click();
		Thread.sleep(5000);
		File obj = new File(System.getProperty("user.dir"));
		File[] listOfFiles = obj.listFiles();
		boolean fileFound = false;
		File obj_File = null;
		for (File IndividualFile : listOfFiles) {
			String FileName = IndividualFile.getName();
			System.out.println(FileName);
			if (FileName.contains("file-sample_100kB")) {
				fileFound = true;
				obj_File = new File(FileName);
			}
		}
		Assert.assertTrue(fileFound, "File Downloaded Not Found");
		obj_File.deleteOnExit();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("inside afterMethod");
		if(result.getStatus()==ITestResult.FAILURE){
			// to add test case name in extent report
			ExtTest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
			// to add error/exception that occured in extent report
			ExtTest.log(Status.FAIL, "Failed TEST CASE Error Is " + result.getThrowable());
			String screenshotPath = library.takescreeshot(driver, result.getName());
			// adding screen shot in extent report
			ExtTest.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtTest.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtTest.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		startExententReport();
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
		ExtReports.flush();// after this line execution export report will be generated
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

}
