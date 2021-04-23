package com.bsoft.service;

import java.util.List;
import java.util.Map;

import com.bsoft.model.HospitalInformation;
import com.bsoft.model.User;
import com.bsoft.util.PagePojo;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageInfo;

public interface HospitalInformationService {

	PageInfo<HospitalInformation> findAll(Integer pageNo, Integer pageSize, User user);

	List<HospitalInformation> findAll(User user);

	void save(HospitalInformation hos);

	boolean update(HospitalInformation hos);

	int validataOrg(String organizcode);

	List<Map<String, Object>> getAllOrganizationType();

	ResponseBean saveOrganization(HospitalInformation hospitalInformation);

	ResponseBean deleteOrganization(String organizCode);

	PagePojo getHospitalInfoByPage(Integer pageNo, Integer pageSize, Map<String, Object> params);
}
