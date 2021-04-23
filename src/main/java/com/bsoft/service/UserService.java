package com.bsoft.service;

import com.bsoft.model.HospitalInformation;
import com.bsoft.model.Role;
import com.bsoft.model.User;
import com.bsoft.model.UserRole;
import com.bsoft.util.PagePojo;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    PageInfo<User> findAll(Integer pageNo, Integer pageSize, User user);

    PagePojo findAllMS(Integer pageNo, Integer pageSize, User user);

    User findUser(User user);

    User findUserMS(User user);

    void save(User user);

    boolean update(User user);

    boolean updateMS(User user);

    boolean logOff(User user);

    User login(User user);

    User loginMS(User user);

    List<HospitalInformation> getHosInfo(User user);

    List<HospitalInformation> getHosInfoMS(User user);

    boolean delete(int userId);

    int validataUserName(String userName);

    List<UserRole> findUserRoleAll(UserRole userRole);

    List<UserRole> findUserRoleAllMS(UserRole userRole);

    UserRole findUserRole(UserRole userRole);

    List<UserRole> findUserRoleList(UserRole userRole);

    boolean saveUserRole(UserRole userRole);

    boolean deleteUserRole(UserRole userRole);

    boolean saveUserRoleMS(UserRole userRole);

    boolean deleteUserRoleMS(UserRole userRole);

    ResponseBean saveRole(Role role);

    PageInfo<Role> getRoleWithPage(Integer pageNo, Integer pageSize, String roleName);

    void deleteRoleById(String id);

    void updateUserRoleMS(UserRole userRole);

    void updateUser(User user);

    ResponseBean saveUser(User user);

    User getUserByUserId(String userId);
}
