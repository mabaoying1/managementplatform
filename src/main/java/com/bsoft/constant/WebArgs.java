package com.bsoft.constant;

import org.springframework.stereotype.Component;

public class WebArgs extends Args {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3442687932000506797L;
	private String themeID;
    private String dimExpenseType;

	public String getThemeID() {
		return themeID;
	}

	public void setThemeID(String themeID) {
		this.themeID = themeID;
	}

	public String getDimExpenseType() {
		return dimExpenseType;
	}

	public void setDimExpenseType(String dimExpenseType) {
		this.dimExpenseType = dimExpenseType;
	} 
}
