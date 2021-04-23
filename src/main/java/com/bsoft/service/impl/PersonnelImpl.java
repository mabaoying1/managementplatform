package com.bsoft.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsoft.mapper.PersonnelMapper;
import com.bsoft.model.Personnel;
import com.bsoft.service.PersonnelService;
import com.bsoft.util.PagePojo;
import com.bsoft.util.PageUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mabaoying
 * @ClassName: PersonnelImpl
 * @Description: 人员管理业务逻辑层
 * @date 2019年03月12日
 * @最后修改人:
 * @最后修改时间:
 */
@Service
@Transactional
public class PersonnelImpl implements PersonnelService {

	@Autowired
	private PersonnelMapper personnelMapper;

	/**
	 * 分页获取人员信息
	 */
	@Override
	public PagePojo getPersonnelWithPage(Integer pageNo, Integer pageSize, Map<String, Object> paramMap) {
		PageHelper.startPage(pageNo, pageSize);
		List<Personnel> list = personnelMapper.getPersonnel(paramMap);
		return PageUtil.ObjectPageInfo(list);
	}

	/**
	 * 删除人员信息
	 */
	@Override
	public void deletePersonnel(String personId) {
		personnelMapper.deletePersonnel(personId);
	}

}
