package com.bsoft.constant;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.google.common.collect.Lists;
public class ShowColumn  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1732412921995825893L;
	private List<Column>  elumn=Lists.newArrayList();

	private String id;
	
	
	public ShowColumn(){
		Column col=new Column(Constant.DIMENSION_KEY,Constant.DIMENSION_DATE);
		elumn.add(col);
	}
	@XmlElement(name = "elumn") 
	public  List<Column>  getElumn() {
		return elumn;
	}

	public void setElumn( List<Column>  elumn) {
		this.elumn = elumn;
	}
	
	@XmlAttribute(name = "id") 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
