package com.bsoft.model;

public class ResHospital {

	private String officeDimCode;
	private String orgDimCode;
	private String userDimCode;
	public String getOfficeDimCode() {
		return officeDimCode.toUpperCase();
	}
	public void setOfficeDimCode(String officeDimCode) {
		this.officeDimCode = officeDimCode;
	}
	public String getOrgDimCode() {
		return orgDimCode.toUpperCase();
	}
	public void setOrgDimCode(String orgDimCode) {
		this.orgDimCode = orgDimCode;
	}
	public String getUserDimCode() {
		return userDimCode.toUpperCase();
	}
	public void setUserDimCode(String userDimCode) {
		this.userDimCode = userDimCode;
	}

}
