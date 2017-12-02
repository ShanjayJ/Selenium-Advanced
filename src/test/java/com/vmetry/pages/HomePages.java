package com.vmetry.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.vmetry.locators.HomeLocator;

public class HomePages extends HomeLocator {
	private WebDriver driver;
	private Select vechiType;
	
	public HomePages(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super();
		this.driver = driver;
	}
	
	public void bookTaxi(String name, String mobNum , String pickup , String dropPoint , String carType) {
		driver.findElement(By.id(NAME_ID)).sendKeys(name);
		driver.findElement(By.id(MOB_NUMB_NAME)).sendKeys(mobNum);
		driver.findElement(By.id(PICK_UP_PLACE_ID)).sendKeys(pickup);
		driver.findElement(By.id(DROP_PLACE_ID)).sendKeys(dropPoint);
		vechiType = new Select(driver.findElement(By.id(CAR_TYPE_ID)));
		vechiType.selectByVisibleText(carType);
	}
	
	public void login() {
		driver.findElement(By.linkText(LOGIN_PG)).click();
	}

}
