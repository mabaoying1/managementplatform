package com.bsoft.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bsoft.model.HospitalInformation;
import com.bsoft.model.User;

public interface HospitalInformationMapper {

	List<HospitalInformation> findAll(User user);

	int saveHospitalInfo(HospitalInformation hos);

	boolean updateHospitalInfo(HospitalInformation hos);

	int validataOrg(String organizcode);

	/**
	 * 获取全部机构类型
	 */
	List<Map<String, Object>> getAllOrganizationType();

	/**
	 * 通过机构代码查询机构信息
	 */
	HospitalInformation getHosInfoByOrganizationCode(@Param("organizCode") String organizCode);

	/**
	 * 通过当前上级部门id获取所以下级部门信息
	 */
	List<HospitalInformation> getHosInfoByParentId(@Param("parentId") String organizCode);

	/*
	 * 通过机构编码删除机构信息
	 */
	int deleteOrganization(@Param("organizCode") String organizCode);

	/**
	 * 动态获取医院列表
	 */
	List<HospitalInformation> getHospitalInfoByPage(Map<String, Object> params);
}
