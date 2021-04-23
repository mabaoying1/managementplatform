package com.bsoft.model;

import java.io.Serializable;

/**
 * 用户
 * 
 * @author tanc
 *
 */
public class OfficeInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 468706860633999028L;
	private String id;// 编号
	private String officeCode;// 科室代码
	private String officeName;// 科室名称
	private String organizCode;// 所属机构
	private String organizType;// 科室类型
	private String parentId;// 上级科室
	private String address;// 地址
	private String outPatientClinic;// 门诊科室
	private String medicalLab;// 医技科室
	private String hospitaldept;// 住院科室
	private String hospitalArea;// 住院病区
	private String mtmanage;// 门特使用
	private int ratedBed;// 额定床位
	private String telphone;// 联系电话
	private String email;// 电子邮箱
	private String plsx;// 排列顺序
	private String pyCode;// 拼音助记
	private String ybdz;// 医保对照编码
	private String ybdzmc;// 医保对照名称
	private String logoff;// 状态
	// accountoffice,
	// logoff,
	// orgmappingid,
	// platformcode

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOrganizCode() {
		return organizCode;
	}

	public void setOrganizCode(String organizCode) {
		this.organizCode = organizCode;
	}

	public String getOrganizType() {
		return organizType;
	}

	public void setOrganizType(String organizType) {
		this.organizType = organizType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOutPatientClinic() {
		return outPatientClinic;
	}

	public void setOutPatientClinic(String outPatientClinic) {
		this.outPatientClinic = outPatientClinic;
	}

	public String getMedicalLab() {
		return medicalLab;
	}

	public void setMedicalLab(String medicalLab) {
		this.medicalLab = medicalLab;
	}

	public String getHospitaldept() {
		return hospitaldept;
	}

	public void setHospitaldept(String hospitaldept) {
		this.hospitaldept = hospitaldept;
	}

	public String getHospitalArea() {
		return hospitalArea;
	}

	public void setHospitalArea(String hospitalArea) {
		this.hospitalArea = hospitalArea;
	}

	public String getMtmanage() {
		return mtmanage;
	}

	public void setMtmanage(String mtmanage) {
		this.mtmanage = mtmanage;
	}

	public int getRatedBed() {
		return ratedBed;
	}

	public void setRatedBed(int ratedBed) {
		this.ratedBed = ratedBed;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlsx() {
		return plsx;
	}

	public void setPlsx(String plsx) {
		this.plsx = plsx;
	}

	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public String getYbdz() {
		return ybdz;
	}

	public void setYbdz(String ybdz) {
		this.ybdz = ybdz;
	}

	public String getYbdzmc() {
		return ybdzmc;
	}

	public void setYbdzmc(String ybdzmc) {
		this.ybdzmc = ybdzmc;
	}

	public String getLogoff() {
		return logoff;
	}

	public void setLogoff(String logoff) {
		this.logoff = logoff;
	}

	@Override
	public String toString() {
		return "OfficeInformation [id=" + id + ", officeCode=" + officeCode + ", officeName=" + officeName
				+ ", organizCode=" + organizCode + ", organizType=" + organizType + ", parentId=" + parentId
				+ ", address=" + address + ", outPatientClinic=" + outPatientClinic + ", medicalLab=" + medicalLab
				+ ", hospitaldept=" + hospitaldept + ", hospitalArea=" + hospitalArea + ", mtmanage=" + mtmanage
				+ ", ratedBed=" + ratedBed + ", telphone=" + telphone + ", email=" + email + ", plsx=" + plsx
				+ ", pyCode=" + pyCode + ", ybdz=" + ybdz + ", ybdzmc=" + ybdzmc + ", logoff=" + logoff + "]";
	}
}
