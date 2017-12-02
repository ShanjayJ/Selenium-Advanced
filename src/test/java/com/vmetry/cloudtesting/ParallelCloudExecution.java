package com.vmetry.cloudtesting;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelCloudExecution {
	WebDriver driver;
	Dimension dim;
	WebDriverWait wait;

	@BeforeTest
	@Parameters(value = { "browser", "version", "platform" })
	public void setUp(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform", platform);
		capability.setCapability("browserName", browser);
		capability.setCapability("browserVersion", version);
		capability.setCapability("project", "P1");
		capability.setCapability("build", "1.0");
		driver = new RemoteWebDriver(
				new URL("https://shanjayj1:UPppm6bFb8aZV8ENzAFJ@hub-cloud.browserstack.com/wd/hub"), capability);
	}

	@Test
	public void doAutoComplete() {
		driver.get("http://jqueryui.com/");
		// driver.manage().window().setSize(dim);
		driver.findElement(By.xpath(".//*[@id='sidebar']/aside[2]/ul/li[2]/a")).click();
		wait = new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.titleContains("Autocomplete"));
		String input = "haskell";
		WebElement frame, srchBox;
		frame = driver.findElement(By.className("demo-frame"));
		//driver.switchTo().frame(frame);
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

		driver.findElement(By.id("tags")).sendKeys("E");
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ui-menu-item")));

		List<WebElement> autoCpltOpt = driver.findElements(By.className("ui-menu-item"));
		for (WebElement opt : autoCpltOpt) {
			if (opt.getText().trim().equalsIgnoreCase(input)) {
				opt.click();
				break;
			}
		}
		srchBox = driver.findElement(By.id("tags"));
		 Assert.assertEquals(srchBox.getAttribute("Value"), input);
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}

}
