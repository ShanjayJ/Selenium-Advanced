package com.vmetry.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryLocators {

	@FindBy(id = "nameid")
	protected WebElement passengerName;

	@FindBy(id = "mobid")
	protected WebElement passengerMobNum;

	@FindBy(id = "pickid")
	protected WebElement passengerPickUp;

	@FindBy(id = "dropid")
	protected WebElement passengerDropup;

	@FindBy(id = "vehid")
	protected WebElement passengerVechicleType;
	

	public PageFactoryLocators(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

}
