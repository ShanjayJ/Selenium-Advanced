package com.vmetry.reports;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	WebDriver driver;
	WebDriverWait wait;
	ExtentReports report;
	ExtentTest test;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.gecko.driver",
				"F:\\Java Files\\Browser Drivers\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		report = new ExtentReports("F:\\Java Files\\Source code\\SeleniumAdvanced\\Extent Report\\extent-report.html",
				true);
		driver.get("http://jqueryui.com/autocomplete/");
		Dimension dim = new Dimension(1800, 1000);
		driver.manage().window().setSize(dim);
		wait = new WebDriverWait(driver, 10);
	}

	@Test(priority = 0)
	public void inputNoiseWord() {
		WebElement frame, inputBox;
		test = report.startTest("Input Noise Word");
		frame = driver.findElement(By.className("demo-frame"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		test.log(LogStatus.INFO, "Successfully jumped into frame");
		inputBox = driver.findElement(By.id("tags"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tags")));
		inputBox.sendKeys("E");
		test.log(LogStatus.INFO, "Noise Word Entered");
	}

	@Test(priority = 1)
	public void chooseSugg() {
		String input = "haskell";
		test = report.startTest("Choosing Suggestion");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ui-menu-item")));
		test.log(LogStatus.INFO, "Waiting for Suggestion");
		List<WebElement> autoCpltOpt = driver.findElements(By.className("ui-menu-item"));
		for (WebElement opt : autoCpltOpt) {
			if (opt.getText().trim().equalsIgnoreCase(input)) {
				opt.click();
				test.log(LogStatus.INFO, "Choosing Suggestion");
				break;
			}
		}
		test.log(LogStatus.INFO, "Asseting suggestion");
		Assert.assertEquals(driver.findElement(By.id("tags")).getAttribute("value"), input);
		test.log(LogStatus.INFO, "Asseting suggestion Passed");
	}

	@AfterMethod
	public void publish(ITestResult rslt) throws IOException {
		String screenshot, img;
		File snap;

		screenshot = "F:\\Java Files\\Source code\\SeleniumAdvanced\\Extent Report\\" + rslt.getName() + ".png";
		if (rslt.getStatus() == ITestResult.SUCCESS) {
			snap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(snap, new File(screenshot));
			img = test.addScreenCapture(screenshot);
			test.log(LogStatus.PASS, "Test Case " + rslt.getName() + " passed", img);
		} else if (rslt.getStatus() == ITestResult.FAILURE) {
			snap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(snap, new File(screenshot));
			img = test.addScreenCapture(screenshot);
			test.log(LogStatus.PASS, "Test Case " + rslt.getName() + " passed", img);
		}
		report.endTest(test);
		report.flush();
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}
