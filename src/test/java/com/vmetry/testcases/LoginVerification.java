package com.vmetry.testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginVerification {
    WebDriver driver;
	Dimension dim;
	WebDriverWait wait;
	JavascriptExecutor jse;
	Actions act;
	SoftAssert s_asrt;

	@BeforeMethod
	@Parameters({ "browser" })
	public void setup(String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "driver" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "driver" + File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		wait = new WebDriverWait(driver, 10);
		act = new Actions(driver);
		driver.get("https://cashkaro.iamsavings.co.uk/");

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		s_asrt = new SoftAssert();

		dim = new Dimension(1800, 1800);
		driver.manage().window().setSize(dim);

	}

	@Test(priority = 1, dataProvider = "LoginVerification Data", dataProviderClass = TestDataProvider.class, description = "Lauch the app and Perform SignUP")
	public void signUpAndJoin(String userSlec, String fname, String emailID, String passWrd, String fbEmailID,
			String fbPasswrd) throws Exception {

		driver.findElement(By.id("link_join")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (userSlec.trim().equalsIgnoreCase("Normal")) {
			normalSignIn(fname, emailID, passWrd);

		} else

		if (userSlec.trim().equalsIgnoreCase("Facebook")) {
			fbSignIn(fbEmailID, fbPasswrd);

		}

	}

	@Test(priority = 2, dataProvider = "LoginVerification Data", dataProviderClass = TestDataProvider.class, description = "Perform SignIN", dependsOnMethods = {
			"signUpAndJoin" })
	public void doSignIn(String userSlec, String fname, String emailID, String passWrd, String fbEmailID,
			String fbPasswrd) throws Exception {

		WebElement signInClck, signInMail, signInPass, signInButton;

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='link_signin']")));

		signInClck = driver.findElement(By.xpath(".//*[@id='link_signin']"));
		signInClck.click();

		signInMail = driver.findElement(By.id("uname"));
		signInMail.sendKeys(emailID);

		signInPass = driver.findElement(By.id("spwd-txt"));
		act.moveToElement(signInPass).click().sendKeys(passWrd).build().perform();

		Thread.sleep(3000);

		signInButton = driver.findElement(By.id("btnLayoutSignIn"));
		signInButton.click();

	}

	@Test(priority = 3, dataProvider = "LoginVerification Data", dataProviderClass = TestDataProvider.class, description = "Forgot Password Automation")
	public void frgtPass(String userSlec, String fname, String emailID, String passWrd, String fbEmailID,
			String fbPasswrd) throws Exception {

		WebElement signInClck, frgtPass, enterMail, rsetPass;
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//*[@id='link_signin']")));

		signInClck = driver.findElement(By.xpath(".//*[@id='link_signin']"));
		signInClck.click();
				
		
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		for (String str : windows) {
			System.out.println(str);
		}
		driver.switchTo().window(windows.get(windows.size() - 1));

		frgtPass = driver.findElement(By.id("lnkLayoutForgotPassword"));
		frgtPass.click();

		driver.switchTo().window(windows.get(windows.size() - 1));

		enterMail = driver.findElement(By.id("from_email"));
		enterMail.sendKeys(emailID);

		rsetPass = driver.findElement(By.id("submit_req"));
		rsetPass.click();

		driver.close();

	}
	


	@AfterMethod
	public void close() {
		if (!(driver == null)) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.quit();
		}

	}

	public void normalSignIn(String fName, String emailId, String passwrd) throws Exception {

		WebElement targetArea, name, email, cEmail, pass, valueChk, join;

		targetArea = driver.findElement(By.xpath("html/body/div[1]/section/div[2]/div[2]/i"));
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", targetArea);

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("firstname")));

		name = driver.findElement(By.id("firstname"));
		name.sendKeys(fName);

		email = driver.findElement(By.id("email"));
		email.sendKeys(emailId);

		cEmail = driver.findElement(By.xpath(".//*[@id='con_email']"));
		cEmail.sendKeys(emailId);

		pass = driver.findElement(By.xpath(".//*[@id='pwd-txt']"));
		act.moveToElement(pass);
		act.click();
		act.sendKeys(passwrd);
		act.build().perform();

		valueChk = driver.findElement(By.id("to_be_check"));
		String captchaValue = JOptionPane.showInputDialog("Please enter the captcha value:");
		valueChk.sendKeys(captchaValue);

		join = driver.findElement(By.id("join_free_submit"));
		join.click();

	}

	public void fbSignIn(String fbEmailID, String fbPasswrd) throws Exception {

		WebElement selecFb, fbEmail, fbPass, fbLogin, joinClck;

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("close_and_go_fb")));
		selecFb = driver.findElement(By.id("close_and_go_fb"));
		selecFb.click();

		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		for (String str : windows) {
			System.out.println(str);
		}
		driver.switchTo().window(windows.get(windows.size() - 1));

		fbEmail = driver.findElement(By.id("email"));
		fbEmail.sendKeys(fbEmailID);

		Thread.sleep(3000);

		fbPass = driver.findElement(By.xpath(".//*[@id='pass']"));
		fbPass.sendKeys(fbPasswrd);

		fbLogin = driver.findElement(By.id("u_0_0"));
		fbLogin.click();

		joinClck = driver.findElement(By.xpath(".//*[@id='u_0_4']/div[2]/div[1]/div[1]/button"));
		joinClck.click();

		String title = driver.getCurrentUrl();

		s_asrt.assertTrue(title.contains("Facebook"));
		s_asrt.assertAll();

	}

}
