package com.bsoft.constant;

import java.util.Date;

import com.bsoft.util.DateUtil;

public  class Args extends Transform implements Arg{
	
	/**
	 * 
	 */
	public String startdate=DateUtil.formatDate(new Date());
	public String enddate=DateUtil.formatDate(new Date());
	public String dimDoctor;
    public String dimDept;
    /**
     * "2019-01"
     */
    public String dimMonth;
    public String dimYear;
    public String dimQuarter;
    //注释掉机构的属性，解决单机构的问题
  //  public String dimOrganization;
    
	
	public String getDimDoctor() {
		return dimDoctor;
	}
	public void setDimDoctor(String dimDoctor) {
		this.dimDoctor = dimDoctor;
	}
	public String getDimDept() {
		return dimDept;
	}
	public void setDimDept(String dimDept) {
		this.dimDept = dimDept;
	}
	public String getDimMonth() {
		return dimMonth;
	}
	public void setDimMonth(String dimMonth) {
		this.dimMonth = dimMonth;
	}
	public String getDimYear() {
		return dimYear;
	}
	public void setDimYear(String dimYear) {
		this.dimYear = dimYear;
	}
	public String getDimQuarter() {
		return dimQuarter;
	}
	public void setDimQuarter(String dimQuarter) {
		this.dimQuarter = dimQuarter;
	}
//	public String getDimOrganization() {
//		return dimOrganization;
//	}
//	public void setDimOrganization(String dimOrganization) {
//		this.dimOrganization = dimOrganization;
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8177632180513955411L;


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
	@Override
	public String getThemeID() {
		return null;
	}
}
