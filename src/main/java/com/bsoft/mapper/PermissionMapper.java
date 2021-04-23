package com.bsoft.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bsoft.model.MenuEntity;

/**
 * @author mabaoying
 * @ClassName: PermissionMapper
 * @Description: 权限管理dao层
 * @date 2019年03月15日
 * @最后修改人:
 * @最后修改时间:
 */
public interface PermissionMapper {
	/**
	 * 查询菜单
	 */
	List<MenuEntity> getAllMenu();

	/**
	 * 删除菜单
	 */
	int deleteMenu(String id);

	/**
	 * 
	 * 新增菜单
	 */
	int addMenu(MenuEntity menu);

	/**
	 * 
	 * 更新菜单
	 */
	int updateMenu(MenuEntity menu);

	/**
	 * 获取角色菜单
	 */
	List<Map<String, Object>> getRoleMenu(@Param("roleId") String roleId);

	/**
	 * 通过角色id删除角色菜单
	 */
	int deleteRoleMenuByRoleId(@Param("roleId") String roleId);

	/**
	 * 新增角色菜单
	 */
	int addRoleMenu(@Param("roleId") String roleId, @Param("menuIds") String[] menuIds);

	/**
	 * 通过角色id查询菜单
	 */
	List<MenuEntity> getMenuTreeByRoleId(String roleId);

}
