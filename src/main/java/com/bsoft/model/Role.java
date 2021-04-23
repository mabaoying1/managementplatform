package com.bsoft.model;

import java.io.Serializable;

/**
 * @author mabaoying
 * @ClassName: Role
 * @Description: 系统角色实体类
 * @date 2019年03月12日
 * @最后修改人:
 * @最后修改时间:
 */
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleDesc;
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
