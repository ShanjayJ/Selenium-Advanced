package com.vmetry.cloudtesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CloudParallelExe {
	WebDriver driver;
	Dimension dim;
	WebDriverWait wait;
	SoftAssert s_assert;
	Actions act;
	JavascriptExecutor jse;
	public static final String USERNAME = "shanjayj1";
	  public static final String AUTOMATE_KEY = "UPppm6bFb8aZV8ENzAFJ";
	  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@BeforeTest(groups = { "all" })
	public void stup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", "Safari");
	caps.setCapability("browser_version", "11.0");
	caps.setCapability("os", "OS X");
	caps.setCapability("os_version", "High Sierra");
	caps.setCapability("resolution", "1024x768");
	    driver = new RemoteWebDriver(new URL(URL), caps);
		wait = new WebDriverWait(driver, 10);
		dim = new Dimension(1800, 1800);
		driver.get("http://jqueryui.com/");
		driver.manage().window().maximize();
		act = new Actions(driver);
	}

	@Test(groups = { "regression" })
	public void dragAndDrop() throws InterruptedException {
		WebElement droppableMnu = driver.findElement(By.linkText("Droppable"));
		droppableMnu.click();
		WebElement src, dest, frame;
		frame = driver.findElement(By.className("demo-frame"));
		Thread.sleep(10000);
		driver.switchTo().frame(frame);
		src = driver.findElement(By.id("draggable"));
		dest = driver.findElement(By.id("droppable"));
		Actions act = new Actions(driver);
		// act.clickAndHold(src).moveToElement(dest).release(dest).build().perform();
		act.dragAndDrop(src, dest).build().perform();
		driver.switchTo().defaultContent();

	}

	@Test(groups = { "regression", "sanity" })
	public void Slider() throws InterruptedException {
		WebElement button = driver.findElement(By.xpath(".//*[@id='sidebar']/aside[2]/ul/li[11]/a"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", button);
		driver.findElement(By.partialLinkText("Slid")).click();
		WebElement frame, sliderHandler, sliderScale;
		;
		frame = driver.findElement(By.className("demo-frame"));
		Thread.sleep(10000);
		driver.switchTo().frame(frame);
		sliderHandler = driver.findElement(By.xpath(".//*[@id='slider']/span"));
		sliderScale = driver.findElement(By.id("slider"));
		int sliderWidth = sliderScale.getSize().width;
		int moveTo = 495;
		if (moveTo <= sliderWidth) {
			Actions act = new Actions(driver);
			act.clickAndHold(sliderHandler).moveByOffset(moveTo, 0).release().build().perform();
		}
		driver.switchTo().defaultContent();
	}

	@Test(groups = { "sanity" })
	public void ResizeElement() throws InterruptedException {
		driver.findElement(By.linkText("Resizable")).click();
		WebElement frame, resizeCtrl, resizeHndl;
		frame = driver.findElement(By.className("demo-frame"));
		Thread.sleep(5000);
		driver.switchTo().frame(frame);
		resizeCtrl = driver.findElement(By.id("resizable"));
		resizeHndl = driver.findElement(By.xpath(".//*[@id='resizable']/div[3]"));
		int height, width;
		height = resizeCtrl.getSize().height;
		width = resizeHndl.getSize().width;
		System.out.println("height:" + height);
		System.out.println("Width:" + width);
		Actions act = new Actions(driver);
		act.clickAndHold(resizeHndl).moveByOffset(width + 100, 0).release(resizeHndl).build().perform();
		driver.switchTo().defaultContent();
	}

	@Test(groups = { "regression", "sanity" })
	public void autoComplete() throws InterruptedException {
		WebElement auto = driver.findElement(By.xpath(".//*[@id='sidebar']/aside[2]/ul/li[2]/a"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", auto);
		driver.findElement(By.xpath(".//*[@id='sidebar']/aside[2]/ul/li[2]/a")).click();
		WebElement frame;
		frame = driver.findElement(By.className("demo-frame"));
		Thread.sleep(5000);
		driver.switchTo().frame(frame);
		driver.findElement(By.id("tags")).sendKeys("E");
		Thread.sleep(3000);
		List<WebElement> autoCpltOpt = driver.findElements(By.className("ui-menu-item"));
		for (WebElement opt : autoCpltOpt) {
			if (opt.getText().trim().equalsIgnoreCase("Haskell")) {
				opt.click();
				break;
			}
		}
		driver.switchTo().defaultContent();
	}

	@Test(groups = { "regression" })
	public void htmlToolTip() {
		WebElement HtmlToolTip = driver.findElement(By.cssSelector(".logo>a"));
		System.out.println("HTML Tool Tip :" + HtmlToolTip.getAttribute("title"));
	}

	@Test(groups = { "regression", "sanity" })
	public void jQueryToolTip() throws InterruptedException {
		WebElement button = driver.findElement(By.xpath(".//*[@id='sidebar']/aside[2]/ul/li[14]/a"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", button);
		WebElement tooltip = driver.findElement(By.linkText("Tooltip"));
		act.moveToElement(tooltip).click().build().perform();
		WebElement frame;
		frame = driver.findElement(By.className("demo-frame"));
		Thread.sleep(5000);
		driver.switchTo().frame(frame);
		WebElement ageTxtBox = driver.findElement(By.cssSelector("#age"));
		act.moveToElement(ageTxtBox).build().perform();
		Thread.sleep(3000);
		WebElement jToolTip = driver.findElement(By.cssSelector("div[class$='-content']"));
		System.out.println("JQuery ToolTip:" + jToolTip.getText());
		Thread.sleep(3000);

	}

	@AfterTest(groups = { "all" })
	public void tearDown() throws InterruptedException {
		if (!(driver == null)) {
			Thread.sleep(3000);
			driver.quit();
		}
	}

}
