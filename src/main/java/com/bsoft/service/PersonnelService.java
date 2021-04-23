package com.bsoft.service;

import java.util.Map;

import com.bsoft.util.PagePojo;

public interface PersonnelService {

	PagePojo getPersonnelWithPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap);

	void deletePersonnel(String personId);

}
