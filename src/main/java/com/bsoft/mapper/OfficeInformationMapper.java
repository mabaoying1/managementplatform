package com.bsoft.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bsoft.model.OfficeInformation;
import com.bsoft.model.User;

public interface OfficeInformationMapper {

	List<OfficeInformation> findAll(User user);

	void save(OfficeInformation off);

	boolean update(OfficeInformation off);

	int validataOff(String organizCode, String officeName);

	/*
	 * 查询全部科信息
	 */
	List<OfficeInformation> getAllDepartment(Map<String, Object> params);

	/*
	 * 通过科室编码查询科室信息
	 */
	OfficeInformation getoffInfoByOfficeCode(@Param("officeCode") String officeCode);

	/*
	 * 通过id删除科室信息
	 */
	int deleteOrganization(@Param("id") String id);

	/*
	 * 通过机构编码获取科室信息
	 */
	List<OfficeInformation> getDepartmentByOrganizCode(@Param("organizCode") String organizCode);
}
