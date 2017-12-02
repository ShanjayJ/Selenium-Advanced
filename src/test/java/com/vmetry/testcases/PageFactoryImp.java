package com.vmetry.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vmetry.pages.PageFactoryHomePage;

public class PageFactoryImp {

	WebDriver driver;
	Dimension dim;
	PageFactoryHomePage homePage;

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
						+ "resources" + File.separator + "config" + File.separator + "config.properties");
		prop.load(fis);

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"F:\\Java Files\\Browser Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"F:\\Java Files\\Browser Drivers\\geckodriver-v0.19.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		dim = new Dimension(1800, 1200);
		driver.get(prop.getProperty("AUT_URL"));
		driver.manage().window().setSize(dim);
		homePage = new PageFactoryHomePage(driver);
	}

	@Test
	public void doBooking() {
		homePage.bookTaxi("AAA", "7894561231", "Guindy", "Adyar", "Premium");
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}

	}
}
