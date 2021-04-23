package com.bsoft.constant;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class ExpenseDim {
	
	private List<Map<String,Object>> list=Lists.newArrayList();
	
	private String[] exp;
	
	
	public void setMap2List(Map<String, Object> map){
		this.list.add(map);
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String[] getExp() {
		return exp;
	}

	public void setExp(String[] exp) {
		this.exp = exp;
	}

}
