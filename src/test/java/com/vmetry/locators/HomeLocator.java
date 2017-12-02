package com.vmetry.locators;

public class HomeLocator {
	protected String NAME_ID, MOB_NUMB_NAME, PICK_UP_PLACE_ID, DROP_PLACE_ID, CAR_TYPE_ID, LOGIN_PG;

	public HomeLocator() {
		// TODO Auto-generated constructor stub
		NAME_ID = "nameid";
		MOB_NUMB_NAME = "mobid";
		PICK_UP_PLACE_ID = "pickid";
		DROP_PLACE_ID = "dropid";
		CAR_TYPE_ID = "vehid";
		LOGIN_PG = "Login";

	}

	public String MENU_XPATH(String menuName) {
		String loc;
		loc = "//ul[@class='menu']/li/a[contains(text(),'" + menuName + "')]";
		return loc;

	}

}
