package com.bsoft.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Theme implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 指标key
	 */
	private String indbName_key;
	/**
	 * 指标value
	 */
	private String indbName_value;

	private List<Dim> dims;
	/**
	 * 时间维度名称
	 */
	private String dimTime;
	/**
	 * 机构维度名称
	 */
	private String dimOrganization;
	/**
	 * 科室维度名称
	 */
	private String dimDept;
	/**
	 * 科室维度名称
	 */
	private String dimDeptCW;
	/**
	 * 医生维度名称
	 */
	private String dimDoctor;
	/**
	 * 月份 如：2018-12
	 */
	private String dimMonth;
    /*

     * 
     * <indbName indbName_key="yw_zyxx" indbName_value="indb002248">
	<dimTime>dim0000001</dimTime>
	<dimOrganization>dim0000114</dimOrganization>
	<dimDept>dim0001651</dimDept>
	<dimDoctor>dim0000282</dimDoctor>
	<dimMonth>dim0000003</dimMonth>
	<!-- 费用类型 -->
	<dimExpenseType>dim0000812</dimExpenseType>
</indbName>
     */

	
	
	@XmlElement(name = "dimDeptCW")
	public String getDimDeptCW() {
		return dimDeptCW;
	}

	public void setDimDeptCW(String dimDeptCW) {
		this.dimDeptCW = dimDeptCW;
	}


	@XmlElement(name = "dimMonth")
	public String getDimMonth() {
		return dimMonth;
	}

	public void setDimMonth(String dimMonth) {
		this.dimMonth = dimMonth;
	}

	/**
	 * 类目维度字段
	 */
	@XmlAttribute(name = "indbName_value")
	public String getIndbName_value() {
		return indbName_value;
	}

	public void setIndbName_value(String indbName_value) {
		this.indbName_value = indbName_value;
	}

	@XmlAttribute(name = "indbName_key")
	public String getIndbName_key() {
		return indbName_key;
	}

	public void setIndbName_key(String indbName_key) {
		this.indbName_key = indbName_key;
	}

	@XmlElement(name = "dimTime")
	public String getDimTime() {
		return dimTime;
	}

	public void setDimTime(String dimTime) {
		this.dimTime = dimTime;
	}

	@XmlElement(name = "dimOrganization")
	public String getDimOrganization() {
		return dimOrganization;
	}

	public void setDimOrganization(String dimOrganization) {
		this.dimOrganization = dimOrganization;
	}

	@XmlElement(name = "dimDept")
	public String getDimDept() {
		return dimDept;
	}

	public void setDimDept(String dimDept) {
		this.dimDept = dimDept;
	}

	@XmlElement(name = "dimDoctor")
	public String getDimDoctor() {
		return dimDoctor;
	}

	public void setDimDoctor(String dimDoctor) {
		this.dimDoctor = dimDoctor;
	}

	public List<Dim> getDims() {
		return dims;
	}

	public void setDims(List<Dim> dims) {
		this.dims = dims;
	}
}
