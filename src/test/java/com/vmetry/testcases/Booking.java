
package com.vmetry.testcases;

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

import com.vmetry.pages.HomePages;

public class Booking {
	WebDriver driver;
	Dimension dim;
	HomePages hmepage;

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis;

		fis = new FileInputStream(
				"F:\\Java Files\\Source code\\SeleniumAdvanced\\src\\test\\resources\\config\\config.properties");

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
		prop.load(fis);
		driver.get(prop.getProperty("AUT_URL"));
		driver.manage().window().setSize(dim);
		hmepage = new HomePages(driver);
	}

	@Test
	public void doBooking() {
		hmepage.bookTaxi("AAA", "1234567898", "Guindy", "Anna Nagar", "Premium");

	}
	
	@Test
	public void hmelogin() {
		hmepage.login();
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}

	}

}
