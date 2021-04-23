package com.bsoft.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mabaoying
 * @ClassName: RoleOffice
 * @Description: 角色科室实体类
 * @date 2019年12月31日
 * @最后修改人:
 * @最后修改时间:
 */
public class RoleOffice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private Integer roleLevel=2;

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	private String roleName;
	private String officeCode;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String[] getOfficesList() {
		if (StringUtils.isNotBlank(this.officeCode))
			return officeCode.split(",");
		return null;
	}
}
