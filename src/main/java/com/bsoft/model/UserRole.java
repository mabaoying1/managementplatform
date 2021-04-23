package com.bsoft.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色
 * @author tanc
 *
 */
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 3446090491820175008L;
	//用户 名
	private String userId;
	//用户 名
	private String userName;
	//角色名称
	private String roleName;
	//角色编号
	private String roleId;
	//所属机构
	private String jgid;
	//所属机构
	private List<String> jgids;
	//原角色编号修改时用
	private String oldRoleId;
	//原角色编号修改时用
	private String oldUserName;
	
	public UserRole() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getJgids() {
		return jgids;
	}

	public void setJgids(List<String> jgids) {
		this.jgids = jgids;
	}

	public String getOldRoleId() {
		return oldRoleId;
	}

	public void setOldRoleId(String oldRoleId) {
		this.oldRoleId = oldRoleId;
	}

	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
	}

	public String getJgid() {
		return jgid;
	}

	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jgid == null) ? 0 : jgid.hashCode());
		result = prime * result + ((jgids == null) ? 0 : jgids.hashCode());
		result = prime * result
				+ ((oldRoleId == null) ? 0 : oldRoleId.hashCode());
		result = prime * result
				+ ((oldUserName == null) ? 0 : oldUserName.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		UserRole other = (UserRole) obj;
		if (jgid == null) {
			if (other.jgid != null)
				return false;
		} else if (!jgid.equals(other.jgid))
			return false;
		if (jgids == null) {
			if (other.jgids != null)
				return false;
		} else if (!jgids.equals(other.jgids))
			return false;
		if (oldRoleId == null) {
			if (other.oldRoleId != null)
				return false;
		} else if (!oldRoleId.equals(other.oldRoleId))
			return false;
		if (oldUserName == null) {
			if (other.oldUserName != null)
				return false;
		} else if (!oldUserName.equals(other.oldUserName))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
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

	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", userName=" + userName
				+ ", roleName=" + roleName + ", roleId=" + roleId + ", jgid="
				+ jgid + ", jgids=" + jgids + ", oldRoleId=" + oldRoleId
				+ ", oldUserName=" + oldUserName + "]";
	}
 

	
}
