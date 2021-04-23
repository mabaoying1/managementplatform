package com.bsoft.constant;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class OrderOption extends Transform{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4019945091372946635L;
	private String orderField="";
	private String orderBy="";
	
	@XmlElement(name="orderField")
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	@XmlElement(name="orderBy")
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	
}
