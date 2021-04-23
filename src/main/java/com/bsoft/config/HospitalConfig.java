package com.bsoft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HospitalConfig {
	@Value("${hospital.ywOfficeDim}")
	private String ywOfficeDim;
	@Value("${hospital.cwOfficeDim}")
	private String cwOfficeDim;
	public String getYwOfficeDim() {
		return ywOfficeDim;
	}

	public void setYwOfficeDim(String ywOfficeDim) {
		this.ywOfficeDim = ywOfficeDim;
	}

	public String getCwOfficeDim() {
		return cwOfficeDim;
	}

	public void setCwOfficeDim(String cwOfficeDim) {
		this.cwOfficeDim = cwOfficeDim;
	}

	@Value("${hospital.orgDim}")
	private String orgDim;
	@Value("${hospital.userDim}")
	private String userDim;

	public String getOrgDim() {
		return orgDim.toUpperCase();
	}

	public void setOrgDim(String orgDim) {
		this.orgDim = orgDim;
	}

	public String getUserDim() {
		return userDim.toUpperCase();
	}

	public void setUserDim(String userDim) {
		this.userDim = userDim;
	}

}
