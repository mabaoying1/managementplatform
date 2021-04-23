package com.bsoft.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsoft.mapper.OfficeInformationMapper;
import com.bsoft.model.OfficeInformation;
import com.bsoft.model.User;
import com.bsoft.service.OfficeInformationService;
import com.bsoft.util.ChinesePinyinUtil;
import com.bsoft.util.PagePojo;
import com.bsoft.util.PageUtil;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class OfficeInformationServiceImpl implements OfficeInformationService {

	@Resource
	private OfficeInformationMapper officeInformationMapper;

	@SuppressWarnings("static-access")
	@Override
	public PageInfo<OfficeInformation> findAll(Integer pageNo, Integer pageSize, User user) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<OfficeInformation> findAllList = officeInformationMapper.findAll(user);
		PageInfo<OfficeInformation> page = new PageInfo<OfficeInformation>(findAllList);
		return page;
	}

	public void save(OfficeInformation off) {
		officeInformationMapper.save(off);
	}

	public boolean update(OfficeInformation off) {
		return officeInformationMapper.update(off);
	}

	public int validataOff(String organizcode, String officeName) {
		return officeInformationMapper.validataOff(organizcode, officeName);
	}

	/*
	 * 动态分页查询科室信息
	 */
	@Override
	public PagePojo getDepartmentByPage(Integer pageNo, Integer pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNo, pageSize);
		List<OfficeInformation> findAllList = officeInformationMapper.getAllDepartment(params);
		return PageUtil.ObjectPageInfo(findAllList);
	}

	/**
	 * 更新新增科室信息
	 */
	@Override
	public ResponseBean saveDepartment(OfficeInformation officeInformation) {
		// 转换机构拼音首字母
		officeInformation.setPyCode(ChinesePinyinUtil.getPinYinHeadChar(officeInformation.getOfficeName()));
		// 新增
		if (StringUtils.isBlank(officeInformation.getId())) {
			// 查询该科室代码是否系统注册
			OfficeInformation offInfo = officeInformationMapper
					.getoffInfoByOfficeCode(officeInformation.getOfficeCode());
			if (offInfo != null) {
				return new ResponseBean(false, "该科室代码已注册!");
			}
			officeInformation.setId(officeInformation.getOfficeCode());
			officeInformationMapper.save(officeInformation);
		} else {
			// 更新
			officeInformationMapper.update(officeInformation);
		}
		return new ResponseBean();
	}

	/**
	 * 通过id删除科室信息
	 */
	@Override
	public ResponseBean deleteOrganization(String id) {
		officeInformationMapper.deleteOrganization(id);
		return new ResponseBean();
	}

	/**
	 * 通过机构编码获取科室信息
	 */
	@Override
	public List<OfficeInformation> getDepartmentByOrganizCode(String organizCode) {
		List<OfficeInformation> offInfoList = officeInformationMapper.getDepartmentByOrganizCode(organizCode);
		return offInfoList;
	}

}
