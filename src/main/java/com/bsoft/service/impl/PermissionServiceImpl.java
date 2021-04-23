package com.bsoft.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.bsoft.mapper.PermissionMapper;
import com.bsoft.mapper.RoleMapper;
import com.bsoft.model.Role;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bsoft.model.MenuEntity;
import com.bsoft.service.PermissionService;
import com.bsoft.util.TreeBean;

/**
 * @author mabaoying
 * @ClassName: PermissionServiceImpl
 * @Description: 权限管理业务逻辑层
 * @date 2019年03月15日
 * @最后修改人:
 * @最后修改时间:
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	/**
	 * 获取菜单树
	 */
	@Override
	public List<TreeBean> getMenuTree() {
		List<MenuEntity> list = permissionMapper.getAllMenu();
		List<TreeBean> li = new ArrayList<>();
		for (MenuEntity menu : list) {
			li.add(new TreeBean(menu));
		}
		List<TreeBean> data = formatTree(li);
		return data;
	}

	/**
	 * 组装树形菜单
	 */
	private List<TreeBean> formatTree(List<TreeBean> list) {
		List<TreeBean> treelist = new ArrayList<>();// 拼凑好的json格式的数据
		List<TreeBean> parentnodes = new ArrayList<>();// parentnodes存放所有的父节点
		TreeBean root = list.get(0);
		TreeBean node;
		// 循环遍历oracle树查询的所有节点
		for (int i = 1; i < list.size(); i++) {
			node = list.get(i);
			if (node.getPid().equals(root.getId())) {
				// 为tree root 增加子节点
				parentnodes.add(node);
				root.getChildren().add(node);
			} else {// 获取root子节点的子节点
				parentnodes = getChildrenNodes(parentnodes, node);
				parentnodes.add(node);
			}
		}
		treelist.add(root);
		return treelist;
	}

	/*
	 * 获取子节点
	 */
	private static List<TreeBean> getChildrenNodes(List<TreeBean> parentnodes, TreeBean node) {
		List<TreeBean> list = parentnodes;
		for (int i = parentnodes.size() - 1; i >= 0; i--) {
			TreeBean pnode = parentnodes.get(i);
			// 如果是父子关系，为父节点增加子节点，退出for循环
			if (pnode.getId().equals(node.getPid())) {
				pnode.getChildren().add(node);
				list.set(i, pnode);
			}
		}
		return list;
	}

	/**
	 * 删除菜单
	 */
	@Override
	public void deleteMenu(String id) {
		permissionMapper.deleteMenu(id);
	}

	/**
	 * 保存菜单
	 */
	@Override
	public void saveMenu(MenuEntity menu) {
		if (StringUtils.isBlank(menu.getId())) {
			menu.setStatus("1");
			permissionMapper.addMenu(menu);
		} else {
			permissionMapper.updateMenu(menu);
		}
	}

	/**
	 * 获取角色树形菜单
	 */
	@Override
	public List<Map<String, Object>> getRoleMenu(String roleId) {
		// 获取角色菜单
		List<Map<String, Object>> roleMenuTreeList = permissionMapper.getRoleMenu(roleId);
		return roleMenuTreeList;
	}

	/**
	 * 保存菜单
	 */
	@Override
	public void saveRoleMenu(String roleId, String[] menuIds) {
		// 清空之前的角色菜单
		permissionMapper.deleteRoleMenuByRoleId(roleId);
		if (menuIds.length > 0) {
			permissionMapper.addRoleMenu(roleId, menuIds);
		}
	}

	/**
	 * 通过角色获取菜单树
	 */
	@Override
	public List<TreeBean> getMenuTreeByRoleId(String roleId) {
		List<MenuEntity> list = permissionMapper.getMenuTreeByRoleId(roleId);
		List<TreeBean> li = new ArrayList<>();
		for (MenuEntity menu : list) {
			li.add(new TreeBean(menu));
		}
		List<TreeBean> data;
		if(li.size()>0){
			data = formatTree(li);
		}else{
			data=Collections.emptyList();
		}
		return data;
	}

}
