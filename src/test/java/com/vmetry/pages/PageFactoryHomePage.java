package com.vmetry.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.vmetry.locators.PageFactoryLocators;

public class PageFactoryHomePage extends PageFactoryLocators {

	public PageFactoryHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}

	public void bookTaxi(String name, String MobNum, String pickup, String dropup, String Cartype) {
		Select vechicleType;

		passengerName.sendKeys(name);
		passengerMobNum.sendKeys(MobNum);
		passengerPickUp.sendKeys(pickup);
		passengerDropup.sendKeys(dropup);
		vechicleType = new Select(passengerVechicleType);
		vechicleType.selectByVisibleText(Cartype);

	}

}
