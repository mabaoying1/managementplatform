package com.bsoft.mapper;

import com.bsoft.model.Role;
import com.bsoft.model.RoleOffice;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    /**
     * 查询全部角色
     *
     * @param roleName
     */
    List<Role> getAllRole(@Param("roleName") String roleName);

    /**
     * 通过角色名称查询角色
     */
    Role getRoleByRoleDesc(@Param("roleDesc") String roleDesc);

    /**
     * 新增角色
     */
    int addRole(Role role);

    /**
     * 更新角色信息
     */
    int updateRole(Role role);

    /**
     * 通过id删除角色
     */
    int deleteRoleById(@Param("id") String id);

	int deleteRoleOfficeByroleId(@Param("roleId")String roleId);

	int saveRoleOffice(@Param("roleId")String roleId,@Param("officeCodes") String officeCodes);

	String getOfficeCodeByRoleId(String roleId);

	RoleOffice getOfficeCodeByUserId(String userId);
	List<RoleOffice> getAllUserOfficeCode();
}
