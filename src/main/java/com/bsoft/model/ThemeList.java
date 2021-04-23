package com.bsoft.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="theme")
public class ThemeList {
	private List<Theme> themeNames;

	
	
	@XmlElement(name = "themeName") 
	public List<Theme> getThemeNames() {
		return themeNames;
	}

	public void setThemeNames(List<Theme> themeNames) {
		this.themeNames = themeNames;
	}



	
	
	
	
	
	
}
