package com.bsoft.constant;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class Column  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1420801401647697736L;
	
	public Column(){
		
	}
	
	public Column(String key,String dim){
		this.dim=dim;
		this.key=key;
	}
	
	@XmlAttribute(name = "key") 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@XmlAttribute(name = "dim") 
	public  String  getDim() {
		return dim.toUpperCase();
	}
	public void setDim( String  dim) {
		this.dim = dim;
	}



	private String key;
	private String dim;

	
}
