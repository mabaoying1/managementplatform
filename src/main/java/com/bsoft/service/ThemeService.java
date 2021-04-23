package com.bsoft.service;

import java.util.List;
import java.util.Map;

import com.bsoft.constant.Arg;
import com.bsoft.constant.Args;
import com.bsoft.constant.Theme;

public interface ThemeService {
	
	
	
	

	String getTheme2String(Theme theme);

	List getThemeFormatData(Theme theme);

	List<Map<String, Object>> getThemeListData(Theme theme);

	Map<String, Object> getThemeMapData(Theme theme);

	Object getThemeData(Theme theme);

}
