package com.bsoft.model;

import java.io.Serializable;

/**
 * 医院信息
 * 
 * @author tanc
 *
 */
public class HospitalInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1717463620532103240L;
	private String organizCode;// ;//组织机构代码"
	private String registerNumber;// 登记号"
	private String organizSecondName;// 机构第二名称"
	private String organizName;// 机构名称"
	private String classifyCode;// 机构分类"
	private String organizType;// 机构类型"
	private String adminDivision;// 行政区划"
	private String address;// 地址"
	private String streeCode;// 乡镇街道代码"
	private String streeName;// 乡镇街道名称"
	private String hostCode;// 主办单位"
	private String parentId;// 上级部门"
	private String subCode;// 隶属关系"
	private String zipCode;// 邮政编码"
	private String telphone;// 联系电话"
	private String email;// 电子邮箱"
	private String foundDate;// 单位成立时间"
	private String legal;// 法人代表"
	private String subNum;// 直属分站个数"
	private String stationNum;// 服务站个数"
	private String pyCode;// 拼音助记"
	private String grade;// 机构级别"
	private String institLevel;// 机构等次"
	private String buildingArea;// 建筑面积(㎡)"
	private String chemicalMedNum;// 化学药品种数"
	private String chineseMedNum;// 中成药品种数"
	private String director;// 负责人"
	private String logoff;// 状态"
	private String website;// 单位网站"
	private String plsx;
	private String JGID;// 机构代码"
	private String URL;// 公卫地址"
	private String PHISURL;// HIS地址"

	public String getOrganizCode() {
		return organizCode;
	}

	public void setOrganizCode(String organizCode) {
		this.organizCode = organizCode;
	}

	public String getOrganizName() {
		return organizName;
	}

	public void setOrganizName(String organizName) {
		this.organizName = organizName;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getOrganizSecondName() {
		return organizSecondName;
	}

	public void setOrganizSecondName(String organizSecondName) {
		this.organizSecondName = organizSecondName;
	}

	public String getClassifyCode() {
		return classifyCode;
	}

	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}

	public String getOrganizType() {
		return organizType;
	}

	public void setOrganizType(String organizType) {
		this.organizType = organizType;
	}

	public String getAdminDivision() {
		return adminDivision;
	}

	public void setAdminDivision(String adminDivision) {
		this.adminDivision = adminDivision;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreeCode() {
		return streeCode;
	}

	public void setStreeCode(String streeCode) {
		this.streeCode = streeCode;
	}

	public String getStreeName() {
		return streeName;
	}

	public void setStreeName(String streeName) {
		this.streeName = streeName;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public String getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getChemicalMedNum() {
		return chemicalMedNum;
	}

	public void setChemicalMedNum(String chemicalMedNum) {
		this.chemicalMedNum = chemicalMedNum;
	}

	public String getChineseMedNum() {
		return chineseMedNum;
	}

	public void setChineseMedNum(String chineseMedNum) {
		this.chineseMedNum = chineseMedNum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getInstitLevel() {
		return institLevel;
	}

	public void setInstitLevel(String institLevel) {
		this.institLevel = institLevel;
	}

	public String getSubNum() {
		return subNum;
	}

	public void setSubNum(String subNum) {
		this.subNum = subNum;
	}

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public String getLogoff() {
		return logoff;
	}

	public void setLogoff(String logoff) {
		this.logoff = logoff;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String jGID) {
		JGID = jGID;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getPHISURL() {
		return PHISURL;
	}

	public void setPHISURL(String pHISURL) {
		PHISURL = pHISURL;
	}

	public String getPlsx() {
		return plsx;
	}

	public void setPlsx(String plsx) {
		this.plsx = plsx;
	}

	@Override
	public String toString() {
		return "HospitalInformation [organizCode=" + organizCode + ", organizName=" + organizName + ", registerNumber="
				+ registerNumber + ", organizSecondName=" + organizSecondName + ", classifyCode=" + classifyCode
				+ ", organizType=" + organizType + ", adminDivision=" + adminDivision + ", address=" + address
				+ ", streeCode=" + streeCode + ", streeName=" + streeName + ", hostCode=" + hostCode + ", parentId="
				+ parentId + ", subCode=" + subCode + ", zipCode=" + zipCode + ", foundDate=" + foundDate
				+ ", telphone=" + telphone + ", email=" + email + ", website=" + website + ", legal=" + legal
				+ ", director=" + director + ", pyCode=" + pyCode + ", buildingArea=" + buildingArea
				+ ", chemicalMedNum=" + chemicalMedNum + ", chineseMedNum=" + chineseMedNum + ", grade=" + grade
				+ ", institLevel=" + institLevel + ", subNum=" + subNum + ", stationNum=" + stationNum + ", logoff="
				+ logoff + ", JGID=" + JGID + ", URL=" + URL + ", PHISURL=" + PHISURL + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((JGID == null) ? 0 : JGID.hashCode());
		result = prime * result + ((PHISURL == null) ? 0 : PHISURL.hashCode());
		result = prime * result + ((URL == null) ? 0 : URL.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((adminDivision == null) ? 0 : adminDivision.hashCode());
		result = prime * result + ((buildingArea == null) ? 0 : buildingArea.hashCode());
		result = prime * result + ((chemicalMedNum == null) ? 0 : chemicalMedNum.hashCode());
		result = prime * result + ((chineseMedNum == null) ? 0 : chineseMedNum.hashCode());
		result = prime * result + ((classifyCode == null) ? 0 : classifyCode.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((foundDate == null) ? 0 : foundDate.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((hostCode == null) ? 0 : hostCode.hashCode());
		result = prime * result + ((institLevel == null) ? 0 : institLevel.hashCode());
		result = prime * result + ((legal == null) ? 0 : legal.hashCode());
		result = prime * result + ((logoff == null) ? 0 : logoff.hashCode());
		result = prime * result + ((organizCode == null) ? 0 : organizCode.hashCode());
		result = prime * result + ((organizName == null) ? 0 : organizName.hashCode());
		result = prime * result + ((organizSecondName == null) ? 0 : organizSecondName.hashCode());
		result = prime * result + ((organizType == null) ? 0 : organizType.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((pyCode == null) ? 0 : pyCode.hashCode());
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
		result = prime * result + ((stationNum == null) ? 0 : stationNum.hashCode());
		result = prime * result + ((streeCode == null) ? 0 : streeCode.hashCode());
		result = prime * result + ((streeName == null) ? 0 : streeName.hashCode());
		result = prime * result + ((subCode == null) ? 0 : subCode.hashCode());
		result = prime * result + ((subNum == null) ? 0 : subNum.hashCode());
		result = prime * result + ((telphone == null) ? 0 : telphone.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HospitalInformation other = (HospitalInformation) obj;
		if (JGID == null) {
			if (other.JGID != null)
				return false;
		} else if (!JGID.equals(other.JGID))
			return false;
		if (PHISURL == null) {
			if (other.PHISURL != null)
				return false;
		} else if (!PHISURL.equals(other.PHISURL))
			return false;
		if (URL == null) {
			if (other.URL != null)
				return false;
		} else if (!URL.equals(other.URL))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (adminDivision == null) {
			if (other.adminDivision != null)
				return false;
		} else if (!adminDivision.equals(other.adminDivision))
			return false;
		if (buildingArea == null) {
			if (other.buildingArea != null)
				return false;
		} else if (!buildingArea.equals(other.buildingArea))
			return false;
		if (chemicalMedNum == null) {
			if (other.chemicalMedNum != null)
				return false;
		} else if (!chemicalMedNum.equals(other.chemicalMedNum))
			return false;
		if (chineseMedNum == null) {
			if (other.chineseMedNum != null)
				return false;
		} else if (!chineseMedNum.equals(other.chineseMedNum))
			return false;
		if (classifyCode == null) {
			if (other.classifyCode != null)
				return false;
		} else if (!classifyCode.equals(other.classifyCode))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (foundDate == null) {
			if (other.foundDate != null)
				return false;
		} else if (!foundDate.equals(other.foundDate))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (hostCode == null) {
			if (other.hostCode != null)
				return false;
		} else if (!hostCode.equals(other.hostCode))
			return false;
		if (institLevel == null) {
			if (other.institLevel != null)
				return false;
		} else if (!institLevel.equals(other.institLevel))
			return false;
		if (legal == null) {
			if (other.legal != null)
				return false;
		} else if (!legal.equals(other.legal))
			return false;
		if (logoff == null) {
			if (other.logoff != null)
				return false;
		} else if (!logoff.equals(other.logoff))
			return false;
		if (organizCode == null) {
			if (other.organizCode != null)
				return false;
		} else if (!organizCode.equals(other.organizCode))
			return false;
		if (organizName == null) {
			if (other.organizName != null)
				return false;
		} else if (!organizName.equals(other.organizName))
			return false;
		if (organizSecondName == null) {
			if (other.organizSecondName != null)
				return false;
		} else if (!organizSecondName.equals(other.organizSecondName))
			return false;
		if (organizType == null) {
			if (other.organizType != null)
				return false;
		} else if (!organizType.equals(other.organizType))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (pyCode == null) {
			if (other.pyCode != null)
				return false;
		} else if (!pyCode.equals(other.pyCode))
			return false;
		if (registerNumber == null) {
			if (other.registerNumber != null)
				return false;
		} else if (!registerNumber.equals(other.registerNumber))
			return false;
		if (stationNum == null) {
			if (other.stationNum != null)
				return false;
		} else if (!stationNum.equals(other.stationNum))
			return false;
		if (streeCode == null) {
			if (other.streeCode != null)
				return false;
		} else if (!streeCode.equals(other.streeCode))
			return false;
		if (streeName == null) {
			if (other.streeName != null)
				return false;
		} else if (!streeName.equals(other.streeName))
			return false;
		if (subCode == null) {
			if (other.subCode != null)
				return false;
		} else if (!subCode.equals(other.subCode))
			return false;
		if (subNum == null) {
			if (other.subNum != null)
				return false;
		} else if (!subNum.equals(other.subNum))
			return false;
		if (telphone == null) {
			if (other.telphone != null)
				return false;
		} else if (!telphone.equals(other.telphone))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
