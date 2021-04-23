package com.bsoft.service;

import java.util.List;
import java.util.Map;

import com.bsoft.model.OfficeInformation;
import com.bsoft.model.User;
import com.bsoft.util.PagePojo;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageInfo;

public interface OfficeInformationService {

	PageInfo<OfficeInformation> findAll(Integer pageNo, Integer pageSize, User user);

	void save(OfficeInformation off);

	boolean update(OfficeInformation off);

	int validataOff(String organizcode, String officeName);

	PagePojo getDepartmentByPage(Integer pageNo, Integer pageSize, Map<String, Object> params);

	ResponseBean saveDepartment(OfficeInformation officeInformation);

	ResponseBean deleteOrganization(String id);

	List<OfficeInformation> getDepartmentByOrganizCode(String organizCode);
}
