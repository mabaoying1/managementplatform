package com.bsoft.mapper;

import com.bsoft.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    void save(User user);

    void update(User user);

    void updateMS(User user);

    boolean delete(User user);

    User findUser(User user);

    User findUserMS(User user);

    User findUserInfo(User user);

    User findUserInfoMS(User user);

    List<User> findAll(User user);

    List<User> findAllMS(User user);

    void deleteAll();

    int validataUserName(String userName);

    List<HospitalInformation> getHosInfo(User user);

    List<HospitalInformation> getHosInfoMS(String userId);

    /**
     * 角色
     */
    void saveUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);

    void saveUserRoleMS(UserRole userRole);

    void deleteUserRoleMS(UserRole userRole);

    UserRole findUserRole(UserRole userRole);

    UserRole findUserRoleMS(UserRole userRole);

    List<UserRole> findUserRoleList(UserRole userRole);

    void deleteUserRole(UserRole userRole);

    List<UserRole> findUserRoleAll(UserRole userRole);

    List<UserRole> findUserRoleAllMS(UserRole userRole);

    /**
     * 新增角色
     */
    int addRole(Role role);

    /**
     * 通过角色名称查询角色
     */
    Role getRoleByRoleDesc(@Param("roleDesc") String roleDesc);

    /**
     * 更新角色信息
     */
    int updateRole(Role role);

    /**
     * 查询全部角色
     *
     * @param roleName
     */
    List<Role> getAllRole(@Param("roleName") String roleName);

    /**
     * 通过id删除角色
     */
    int deleteRoleById(@Param("id") String id);

    /**
     * 添加用户角色
     */
    void addUserRole(UserRole userRole);

    /**
     * 更新系统用户
     */
    void updateUser(User user);

    /**
     * 通过用户id获取系统用户map
     */
    Map<String, Object> getUserMapByUserId(@Param("userId") String userId);

    /**
     * 通过personid获取员工信息
     */
    Personnel getPersonnelByPersonId(@Param("personId") String personId);

    /**
     * 新增系统用户
     */
    int addUser(User user);

    /**
     * 通过用户id获取系统用户
     */
    User getUserByUserId(String userId);

}
