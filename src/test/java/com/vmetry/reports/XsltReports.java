package com.vmetry.reports;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class XsltReports {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "F:\\Java Files\\Browser Drivers\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://jqueryui.com/autocomplete/");
		Dimension dim = new Dimension(1800, 1000);
		driver.manage().window().setSize(dim);
		wait = new WebDriverWait(driver, 10);
		
	}
	
	@Test(priority = 1)
	public void noiseWord() {
		WebElement frame ;
		frame = driver.findElement(By.className("demo-frame"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		driver.findElement(By.id("tags")).sendKeys("E");
		
	}
	
	@Test(priority = 2)
	public void chooseSugg() {
		List<WebElement> autoCpltOpt = driver.findElements(By.className("ui-menu-item"));
		for (WebElement opt : autoCpltOpt) {
			if (opt.getText().trim().equalsIgnoreCase("Haskell")) {
				opt.click();
				break;
			}
		}
		
		Assert.assertEquals(driver.getTitle(), "JQuery UI");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}

}
