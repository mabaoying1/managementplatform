package com.bsoft.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * 
 * @author tanc
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 3446090491820175008L;
	// 主键
	private String userId;
	// 用户 名
	private String userName;

	private String userAge;

	// 密码
	private String password;
	// 密码
	private String oldPassword;
	// 增加时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	// 机构id
	private String jgid;
	// 机构名称
	private String jgmc;
	private String officeCode;// 科室代码
	private String officeName;// 科室名称

	// 权限机构
	private String jgmcQx;
	/**
	 * 默认使用状态true 1，false 0禁用
	 */
	private Integer status = 1;
	// 角色
	private String roleId;
	private Role role;// 角色实体类
	private List<HospitalInformation> hosList;// 权限机构list

	public User() {
		super();
	}

	public String getJgmcQx() {
		return jgmcQx;
	}

	public void setJgmcQx(String jgmcQx) {
		this.jgmcQx = jgmcQx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJgid() {
		return jgid;
	}

	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<HospitalInformation> getHosList() {
		return hosList;
	}

	public void setHosList(List<HospitalInformation> hosList) {
		this.hosList = hosList;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userAge=" + userAge + ", password=" + password
				+ ", createTime=" + createTime + ", jgid=" + jgid + ", jgmc=" + jgmc + ", jgmcQx=" + jgmcQx
				+ ", status=" + status + ", roleId=" + roleId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((jgid == null) ? 0 : jgid.hashCode());
		result = prime * result + ((jgmc == null) ? 0 : jgmc.hashCode());
		result = prime * result + ((jgmcQx == null) ? 0 : jgmcQx.hashCode());
		result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + status;
		result = prime * result + ((userAge == null) ? 0 : userAge.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (jgid == null) {
			if (other.jgid != null)
				return false;
		} else if (!jgid.equals(other.jgid))
			return false;
		if (jgmc == null) {
			if (other.jgmc != null)
				return false;
		} else if (!jgmc.equals(other.jgmc))
			return false;
		if (jgmcQx == null) {
			if (other.jgmcQx != null)
				return false;
		} else if (!jgmcQx.equals(other.jgmcQx))
			return false;
		if (oldPassword == null) {
			if (other.oldPassword != null)
				return false;
		} else if (!oldPassword.equals(other.oldPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (status != other.status)
			return false;
		if (userAge == null) {
			if (other.userAge != null)
				return false;
		} else if (!userAge.equals(other.userAge))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public User(String userName, String password, String jgid, int status, Date createTime) {
		super();
		this.password = password;
		this.userName = userName;
		this.jgid = jgid;
		this.status = status;
		this.createTime = createTime;
	}

	public User(String userId, String userName, String password, String jgid, int status, Date createTime) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.jgid = jgid;
		this.status = status;
		this.createTime = createTime;
	}

	public User(String userId, String userName, String password, String oldPassword, String jgid, int status,
			Date createTime) {
		super();
		this.userId = userId;
		this.password = password;
		this.oldPassword = oldPassword;
		this.userName = userName;
		this.jgid = jgid;
		this.status = status;
		this.createTime = createTime;
	}
}
