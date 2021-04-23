package com.bsoft.constant;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="inds")
public class Inds {
	private List<Ind> inds;

	
	@XmlElement(name = "ind") 
	public List<Ind> getInds() {
		return inds;
	}

	public void setInds(List<Ind> inds) {
		this.inds = inds;
	}

	
	

}
