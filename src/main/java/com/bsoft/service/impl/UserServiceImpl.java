package com.bsoft.service.impl;

import com.bsoft.mapper.UserMapper;
import com.bsoft.model.*;
import com.bsoft.service.UserService;
import com.bsoft.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mabaoying
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @date: 2019/10/22 16:51
 * @最后修改人:
 * @最后修改时间:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static Map<String, User> userCache =new HashMap();

	@Resource
	private UserMapper userMapper;

	public PageInfo<User> findAll(Integer pageNo, Integer pageSize, User user) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<User> findAllList = userMapper.findAll(user);
		PageInfo<User> page = new PageInfo<User>(findAllList);
		for (User map : page.getList()) {
			map.setPassword(MD5Util.decode(user.getPassword() + "", MD5Util.key));
		}
		return page;
	}

	public PagePojo findAllMS(Integer pageNo, Integer pageSize, User user) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> findAllList = userMapper.findAllMS(user);
		return PageUtil.ObjectPageInfo(findAllList);
	}

	@Override
	public User findUser(User user) {
		user = userMapper.findUserInfo(user);
		user.setPassword(MD5Util.decode(user.getPassword() + "", MD5Util.key));
		return user;
	}

	@Override
	public User findUserMS(User user) {
		user = userMapper.findUserInfoMS(user);
		return user;
	}

	@Override
	public List<UserRole> findUserRoleAll(UserRole userRole) {
		List<UserRole> userRoleList = userMapper.findUserRoleAll(userRole);
		return userRoleList;
	}

	@Override
	public List<UserRole> findUserRoleAllMS(UserRole userRole) {
		List<UserRole> userRoleList = userMapper.findUserRoleAllMS(userRole);
		return userRoleList;
	}

	@Override
	public List<UserRole> findUserRoleList(UserRole userRole) {
		List<UserRole> userRoleList = userMapper.findUserRoleList(userRole);
		return userRoleList;
	}

	@Override
	public UserRole findUserRole(UserRole userRole) {
		userRole = userMapper.findUserRole(userRole);
		if (null != userRole) {
			String jgid = userRole.getJgid();
			if (null != jgid && !"".equals(jgid)) {
				String a[] = jgid.split(",");
				List<String> jgids = new ArrayList<String>();
				for (int i = 0; i < a.length; i++) {
					jgids.add(a[i]);
					userRole.setJgids(jgids);
				}
			}
		}
		return userRole;
	}

	public void save(User user) {
		user.setPassword(MD5Util.encode(user.getPassword(), MD5Util.key));
		userMapper.save(user);
	}

	public User login(User user) {
		User userInfo = userMapper.findUser(user);
		return userInfo;
	}

	public User loginMS(User user) {
		User userInfo = userMapper.findUserMS(user);
		return userInfo;
	}

	public List<HospitalInformation> getHosInfo(User user) {
		List<HospitalInformation> hosList = userMapper.getHosInfo(user);
		if (!hosList.isEmpty() && hosList.size() < 4) {
			for (int i = 0; i < hosList.size(); i++) {
				if (null != hosList.get(i)) {
					if (null != hosList.get(i).getOrganizSecondName() && "" != hosList.get(i).getOrganizSecondName()) {
						if ("全院".equals(hosList.get(i).getOrganizSecondName())) {
							hosList.remove(i);
						}
					}
				}
			}
		}
		return hosList;
	}

	public List<HospitalInformation> getHosInfoMS(User user) {
		List<HospitalInformation> hosList = userMapper.getHosInfoMS(user.getUserId());
		if (!hosList.isEmpty() && hosList.size() < 4) {
			for (int i = 0; i < hosList.size(); i++) {
				if (null != hosList.get(i)) {
					if (null != hosList.get(i).getOrganizSecondName() && "" != hosList.get(i).getOrganizSecondName()) {
						if ("全院".equals(hosList.get(i).getOrganizSecondName())) {
							hosList.remove(i);
						}
					}
				}
			}
		}
		return hosList;
	}

	public boolean update(User user) {
		if (null != user.getPassword()) {
			user.setPassword(MD5Util.encode(user.getPassword(), MD5Util.key));
		}
		userMapper.update(user);
		return true;
	}

	public boolean updateMS(User user) {
		if (null != user.getPassword()) {
			user.setPassword(MD5StringUtil.MD5Encode(user.getUserId() + user.getPassword()));
		}
		userMapper.updateMS(user);
		return true;
	}

	public boolean deleteUserRole(UserRole userRole) {
		userMapper.deleteUserRole(userRole);
		return true;
	}

	public boolean saveUserRole(UserRole userRole) {
		userMapper.saveUserRole(userRole);
		return true;
	}

	public boolean deleteUserRoleMS(UserRole userRole) {
		userMapper.deleteUserRoleMS(userRole);
		return true;
	}

	public boolean saveUserRoleMS(UserRole userRole) {
		userMapper.saveUserRoleMS(userRole);
		return true;
	}

	public boolean logOff(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public int validataUserName(String userName) {
		return userMapper.validataUserName(userName);
	}

	/**
	 * 动态分页获取角色
	 */
	@Override
	public PageInfo<Role> getRoleWithPage(Integer pageNo, Integer pageSize, String roleName) {
		PageHelper.startPage(pageNo, pageSize);
		List<Role> allRoleList = userMapper.getAllRole(roleName);
		PageInfo<Role> pageInfo = new PageInfo<Role>(allRoleList);
		return pageInfo;
	}

	/**
	 * 保存角色
	 */
	@Override
	public ResponseBean saveRole(Role role) {
		if (null == role.getId()) {
			Role role2 = userMapper.getRoleByRoleDesc(role.getRoleDesc());
			if (role2 != null) {
				return new ResponseBean(false, "该角色已存在!");
			}
			userMapper.addRole(role);
		} else {
			userMapper.updateRole(role);
		}
		return new ResponseBean();
	}

	/**
	 * 通过id删除角色
	 */
	@Override
	public void deleteRoleById(String id) {
		userMapper.deleteRoleById(id);
	}

	@Override
	public void updateUserRoleMS(UserRole userRole) {
		userMapper.deleteUserRoleMS(userRole);
		if (userRole.getJgids() != null && userRole.getJgids().size() > 0) {
			userMapper.addUserRole(userRole);
		}
	}

	/**
	 * 更新系统用户
	 */
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	/**
	 * 新增系统用户
	 */
	@Override
	public ResponseBean saveUser(User user) {
		// 判断系统用户表是否已注册
		Map<String, Object> userMap = userMapper.getUserMapByUserId(user.getUserId());
		if (userMap != null) {
			return new ResponseBean(false, "该用户名系统已存在!");
		}
		Personnel personnel = userMapper.getPersonnelByPersonId(user.getUserId());
		if (null == personnel) {
			return new ResponseBean(false, "该用户名的员工不存在!");
		}
		user.setUserName(personnel.getPersonName());
		userMapper.addUser(user);
		return new ResponseBean();
	}

	/**
	 * 通过用户名获取用户信息
	 */
	@Override
	public User getUserByUserId(String userId) {

		User user = userCache.get(userId);
		if (null == user) {
			user = userMapper.getUserByUserId(userId);
			userCache.put(userId, user);
		}
		return user ;
	}
}
