package com.bsoft.service.impl;

import com.bsoft.mapper.RoleMapper;
import com.bsoft.model.Role;
import com.bsoft.model.RoleOffice;
import com.bsoft.service.RoleService;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author mabaoying
 * @ClassName: RoleServiceImpl
 * @Description: TODO
 * @date: 2019/10/22 17:36
 * @最后修改人:
 * @最后修改时间:
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	private static Map<String, RoleOffice> roleOfficeMap = Maps.newHashMap();

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 动态分页获取角色
	 */
	@Override
	public PageInfo<Role> getRoleWithPage(Integer pageNo, Integer pageSize, String roleName) {
		PageHelper.startPage(pageNo, pageSize);
		List<Role> allRoleList = roleMapper.getAllRole(roleName);
		PageInfo<Role> pageInfo = new PageInfo<Role>(allRoleList);
		return pageInfo;
	}

	/**
	 * 保存角色
	 */
	@Override
	public ResponseBean saveRole(Role role) {
		if (null == role.getId()) {
			Role role2 = roleMapper.getRoleByRoleDesc(role.getRoleDesc());
			if (role2 != null) {
				return new ResponseBean(false, "该角色已存在!");
			}
			roleMapper.addRole(role);
		} else {
			roleMapper.updateRole(role);
		}
		return new ResponseBean();
	}

	/**
	 * 通过id删除角色
	 */
	@Override
	public void deleteRoleById(String id) {
		roleMapper.deleteRoleById(id);
	}

	@Override
	public String getOfficeCodeByRoleId(String roleId) {
		String officeCode = roleMapper.getOfficeCodeByRoleId(roleId);
		return officeCode;
	}

	@Override
	public void saveRoleOffice(String roleId, String officeCodes) {
		// 先全部清空角色id的科室
		roleMapper.deleteRoleOfficeByroleId(roleId);
		if (org.apache.commons.lang3.StringUtils.isNotBlank(officeCodes)) {
			roleMapper.saveRoleOffice(roleId, officeCodes);
		}
	}

	/**
	 * 通过userId获取科室代码列表
	 */
	@Override
	public RoleOffice getOfficeCodeByUserId(String userId) {
		RoleOffice officeCode = roleOfficeMap.get(userId);
		if (null==officeCode) {
			officeCode = roleMapper.getOfficeCodeByUserId(userId);
			roleOfficeMap.put(userId, officeCode);
		}
		return officeCode;
	}

}
