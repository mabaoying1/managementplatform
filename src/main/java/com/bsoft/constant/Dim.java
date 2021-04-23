package com.bsoft.constant;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import com.bsoft.annotation.Object2MapField;

/**
 * @author mabaoying
 * @ClassName: Dim
 * @Description: 维度实体�?
 * @date: 2019/9/23
 * @�?后修改人:
 * @�?后修改时�?:
 */
public class Dim extends Transform  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4547687580997948341L;

	private String dimDate="DIM0000001";
	private String dimDoctor="";
    private String dimDept="";
    private String dimMonth="DIM0000003";
    private String dimYear="DIM0000005";
    private String dimQuarter="DIM0000004";
    private String dimOrganization="";
    private String startdate="STARTDATE";
    private String enddate="ENDDATE";
    


	@XmlElement(name = "dimDate") 
    public String getDimDate() {
		return dimDate.toUpperCase();
	}
	public void setDimDate(String dimDate) {
		this.dimDate = dimDate;
	}

	@XmlElement(name = "dimDoctor") 
	public String getDimDoctor() {
		return dimDoctor.toUpperCase();
	}
	public void setDimDoctor(String dimDoctor) {
		this.dimDoctor = dimDoctor.toUpperCase();
	}

	@XmlElement(name = "dimDept") 
	public String getDimDept() {
		return dimDept.toUpperCase();
	}
	public void setDimDept(String dimDept) {
		this.dimDept = dimDept.toUpperCase();
	}
	

	@XmlElement(name = "dimMonth") 
	public String getDimMonth() {
		return dimMonth.toUpperCase();
	}
	public void setDimMonth(String dimMonth) {
		this.dimMonth = dimMonth.toUpperCase();
	}

    
    
    public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	@XmlElement(name = "dimYear") 
    public String getDimYear() {
		return dimYear.toUpperCase();
	}
	public void setDimYear(String dimYear) {
		this.dimYear = dimYear.toUpperCase();
	}
	
    @XmlElement(name = "dimQuarter") 
	public String getDimQuarter() {
		return dimQuarter.toUpperCase();
	}
	public void setDimQuarter(String dimQuarter) {
		this.dimQuarter = dimQuarter.toUpperCase();
	}
	
	
    @XmlElement(name = "dimOrganization") 
	public String getDimOrganization() {
		return dimOrganization.toUpperCase();
	}
	public void setDimOrganization(String dimOrganization) {
		this.dimOrganization = dimOrganization.toUpperCase();
	}

	
	


}
