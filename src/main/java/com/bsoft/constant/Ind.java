package com.bsoft.constant;
import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
public class Ind extends Transform  implements Serializable{

	
	private String indId="";
	private String indKey="";
	private String expression="empty";
	private String gatherDim="empty";
	private String  indItemType="normal";
	private String decimalLength="2";
	private String calculateExpression;
	
	private String indUnit="empty";
	private String useSpe="true";
	private String dataSub="empty";
	private String dataOrder="empty";
	private String indName;
	private Map<String, Object> map;
	private String title="";
	
	
	@XmlAttribute(name="title")
	public String getTitle() {
		return title.toUpperCase();
	}
	public void setTitle(String title) {
		this.title = title.toUpperCase();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7146024601746000326L;
	@XmlAttribute(name = "indID")
	public String getIndId() {
		return indId.toUpperCase();
	}
	public void setIndId(String indId) {
		this.indId = indId.toUpperCase();
	}
	@XmlElement(name = "expression")
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@XmlElement(name = "gatherDim")
	public String getGatherDim() {
		return gatherDim;
	}
	public void setGatherDim(String gatherDim) {
		this.gatherDim = gatherDim;
	}
	
	@XmlElement(name = "indItemType")
	public String getIndItemType() {
		return indItemType;
	}
	public void setIndItemType(String indItemType) {
		this.indItemType = indItemType;
	}
	@XmlElement(name = "decimalLength")
	public String getDecimalLength() {
		return decimalLength;
	}
	public void setDecimalLength(String decimalLength) {
		this.decimalLength = decimalLength;
	}
	
	@XmlElement(name = "calculateExpression")
	public String getCalculateExpression() {
		return calculateExpression;
	}
	public void setCalculateExpression(String calculateExpression) {
		this.calculateExpression = calculateExpression;
	}
	
		
		@XmlElement(name = "indUnit")
		public String getIndUnit() {
			return indUnit;
		}
		public void setIndUnit(String indUnit) {
			this.indUnit = indUnit;
		}
		@XmlElement(name = "useSpe")
		public String getUseSpe() {
			return useSpe;
		}
		public void setUseSpe(String useSpe) {
			this.useSpe = useSpe;
		}
		@XmlElement(name = "dataSub")
		public String getDataSub() {
			return dataSub;
		}
		public void setDataSub(String dataSub) {
			this.dataSub = dataSub;
		}
		@XmlElement(name = "dataOrder")
		public String getDataOrder() {
			return dataOrder;
		}
		public void setDataOrder(String dataOrder) {
			this.dataOrder = dataOrder;
		}
		@XmlElement(name = "indName")
		public String getIndName() {
			return indName;
		}
		public void setIndName(String indName) {
			this.indName = indName;
		}
		
		
		@XmlTransient
		public Map<String, Object> getMap() {
			if(null==map){
				this.map=	this.toMap();
			}
			return map;
		}
		public void setMap(Map<String, Object> map) {
			this.map = map;
		}
		
		@XmlAttribute(name="indKey")
		public String getIndKey() {
			return indKey.toUpperCase();
		}
		public void setIndKey(String indKey) {
			this.indKey = indKey;
		}
		
		public String toString(){
			return this.indId+":"+this.indKey+"-"+this.title;
		}
}
