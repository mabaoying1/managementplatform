package com.bsoft.service;

import java.util.List;
import java.util.Map;

import com.bsoft.model.MenuEntity;
import com.bsoft.model.Role;
import com.bsoft.util.ResponseBean;
import com.bsoft.util.TreeBean;
import com.github.pagehelper.PageInfo;

public interface PermissionService {

	List<TreeBean> getMenuTree();

	void deleteMenu(String id);

	void saveMenu(MenuEntity menu);

	List<Map<String, Object>> getRoleMenu(String roleId);

	void saveRoleMenu(String roleId, String[] menuIds);

	List<TreeBean> getMenuTreeByRoleId(String roleId);

}
