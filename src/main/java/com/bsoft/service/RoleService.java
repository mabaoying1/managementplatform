package com.bsoft.service;

import java.util.List;
import java.util.Map;

import com.bsoft.model.Role;
import com.bsoft.model.RoleOffice;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageInfo;

public interface RoleService {
    PageInfo<Role> getRoleWithPage(Integer pageNo, Integer pageSize, String roleName);

    ResponseBean saveRole(Role role);

    void deleteRoleById(String id);

	void saveRoleOffice(String roleId, String officeCodes);

	String getOfficeCodeByRoleId(String roleId);
	
	RoleOffice getOfficeCodeByUserId(String userId);
}
