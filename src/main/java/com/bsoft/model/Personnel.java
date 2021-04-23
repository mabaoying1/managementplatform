package com.bsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mabaoying
 * @ClassName: Personnel
 * @Description: 系统人员实体类
 * @date 2019年03月12日
 * @最后修改人:
 * @最后修改时间:
 */
public class Personnel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personId;// 员工ID
	private String personName;// 姓名
	private String photo;// 头像
	private String cardType;// 证件类型
	private String cardNum;// 证件号码
	private Date birthday;// 生日
	private String gender;// 性别
	private String ethnic;// 民族
	private String homeTown;// 籍贯
	private String email;// email
	private String mobile;// 电话
	private String education;// 最高学历
	private String educationBackgr;// 学位
	private Date joinWork;// 参加工作时间
	private String jobPost;// 行政职务
	private String operationType;// 医师执业类别
	private String operationScope;// 医师执业范围
	private String medicalRoles;// 医疗角色
	private String majorType;// 专业类别
	private String majorJob;// 专业技术职务
	private String officeCode;// 所在科室
	private String officeName;// 科室名称
	private String organizCode;// 所在机构
	private String organizName;// 机构名称
	private String address;// 联系地址
	private String pyCode;// 拼音码
	private String remark;// 备注
	private String certificateNum;// 执业证书编号
	private String majorQualify;// 专业技术资格
	private String majorName;// 所学专业
	private String isExpert;// 专家判别
	private String isCancel;// 作废判别
	private String expertCost;// 专家费
	private String prescribeRight;// 处方权
	private String narcoticRight;// 麻醉药权
	private String psychotropicRight;// 精神药权
	private String antibioticRight;// 抗生素权
	private Date lastModifyDate;// 最后修改时间
	private String logoff;// 注销标志
	private String wbCode;// WBCODE
	private String jxCode;// JXCODE
	private String qtCode;// QTCODE
	private String ygdm;// 员工编号（内码）
	private String userSource;// USERSOURCE
	private Date effectiveTimeBegin;// 角色有效期间（起始）
	private Date effectiveTimeEnd;// 角色有效期间（结束）
	private String birthplace;// 出生地址
	private String assignedPersonID;// 注册人-代码
	private String assignedPersonName;// 注册人-姓名
	private String assignedOfficeCode;// 注册人所属科室代码
	private String assignedOfficeName;// 注册人所属科室名称
	private String assignedContactPerson;// 注册人所属科室联系人姓名

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEducationBackgr() {
		return educationBackgr;
	}

	public void setEducationBackgr(String educationBackgr) {
		this.educationBackgr = educationBackgr;
	}

	public Date getJoinWork() {
		return joinWork;
	}

	public void setJoinWork(Date joinWork) {
		this.joinWork = joinWork;
	}

	public String getJobPost() {
		return jobPost;
	}

	public void setJobPost(String jobPost) {
		this.jobPost = jobPost;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationScope() {
		return operationScope;
	}

	public void setOperationScope(String operationScope) {
		this.operationScope = operationScope;
	}

	public String getMedicalRoles() {
		return medicalRoles;
	}

	public void setMedicalRoles(String medicalRoles) {
		this.medicalRoles = medicalRoles;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

	public String getMajorJob() {
		return majorJob;
	}

	public void setMajorJob(String majorJob) {
		this.majorJob = majorJob;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOrganizCode() {
		return organizCode;
	}

	public void setOrganizCode(String organizCode) {
		this.organizCode = organizCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getMajorQualify() {
		return majorQualify;
	}

	public void setMajorQualify(String majorQualify) {
		this.majorQualify = majorQualify;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getIsExpert() {
		return isExpert;
	}

	public void setIsExpert(String isExpert) {
		this.isExpert = isExpert;
	}

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getExpertCost() {
		return expertCost;
	}

	public void setExpertCost(String expertCost) {
		this.expertCost = expertCost;
	}

	public String getPrescribeRight() {
		return prescribeRight;
	}

	public void setPrescribeRight(String prescribeRight) {
		this.prescribeRight = prescribeRight;
	}

	public String getNarcoticRight() {
		return narcoticRight;
	}

	public void setNarcoticRight(String narcoticRight) {
		this.narcoticRight = narcoticRight;
	}

	public String getPsychotropicRight() {
		return psychotropicRight;
	}

	public void setPsychotropicRight(String psychotropicRight) {
		this.psychotropicRight = psychotropicRight;
	}

	public String getAntibioticRight() {
		return antibioticRight;
	}

	public void setAntibioticRight(String antibioticRight) {
		this.antibioticRight = antibioticRight;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getLogoff() {
		return logoff;
	}

	public void setLogoff(String logoff) {
		this.logoff = logoff;
	}

	public String getWbCode() {
		return wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getJxCode() {
		return jxCode;
	}

	public void setJxCode(String jxCode) {
		this.jxCode = jxCode;
	}

	public String getQtCode() {
		return qtCode;
	}

	public void setQtCode(String qtCode) {
		this.qtCode = qtCode;
	}

	public String getYgdm() {
		return ygdm;
	}

	public void setYgdm(String ygdm) {
		this.ygdm = ygdm;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	public Date getEffectiveTimeBegin() {
		return effectiveTimeBegin;
	}

	public void setEffectiveTimeBegin(Date effectiveTimeBegin) {
		this.effectiveTimeBegin = effectiveTimeBegin;
	}

	public Date getEffectiveTimeEnd() {
		return effectiveTimeEnd;
	}

	public void setEffectiveTimeEnd(Date effectiveTimeEnd) {
		this.effectiveTimeEnd = effectiveTimeEnd;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getAssignedPersonID() {
		return assignedPersonID;
	}

	public void setAssignedPersonID(String assignedPersonID) {
		this.assignedPersonID = assignedPersonID;
	}

	public String getAssignedPersonName() {
		return assignedPersonName;
	}

	public void setAssignedPersonName(String assignedPersonName) {
		this.assignedPersonName = assignedPersonName;
	}

	public String getAssignedOfficeCode() {
		return assignedOfficeCode;
	}

	public void setAssignedOfficeCode(String assignedOfficeCode) {
		this.assignedOfficeCode = assignedOfficeCode;
	}

	public String getAssignedOfficeName() {
		return assignedOfficeName;
	}

	public void setAssignedOfficeName(String assignedOfficeName) {
		this.assignedOfficeName = assignedOfficeName;
	}

	public String getAssignedContactPerson() {
		return assignedContactPerson;
	}

	public void setAssignedContactPerson(String assignedContactPerson) {
		this.assignedContactPerson = assignedContactPerson;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOrganizName() {
		return organizName;
	}

	public void setOrganizName(String organizName) {
		this.organizName = organizName;
	}

}
