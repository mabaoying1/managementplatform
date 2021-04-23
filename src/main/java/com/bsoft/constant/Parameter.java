package com.bsoft.constant;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Parameter {
	private String dim;
	private String dim_key;
	private String value;
	
	@XmlAttribute(name="dim")
	public String getDim() {
		return dim;
	}
	public void setDim(String dim) {
		this.dim = dim;
	}
	@XmlAttribute(name="dim_key")
	public String getDim_key() {
		return dim_key;
	}
	public void setDim_key(String dim_key) {
		this.dim_key = dim_key;
	}

	@XmlAttribute(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
