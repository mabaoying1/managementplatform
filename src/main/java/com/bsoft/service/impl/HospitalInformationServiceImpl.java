package com.bsoft.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bsoft.mapper.HospitalInformationMapper;
import com.bsoft.model.HospitalInformation;
import com.bsoft.model.User;
import com.bsoft.service.HospitalInformationService;
import com.bsoft.util.ChinesePinyinUtil;
import com.bsoft.util.PagePojo;
import com.bsoft.util.PageUtil;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HospitalInformationServiceImpl implements HospitalInformationService {

	@Resource
	private HospitalInformationMapper hospitalInformationMapper;

	@SuppressWarnings("static-access")
	@Override
	public PageInfo<HospitalInformation> findAll(Integer pageNo, Integer pageSize, User user) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<HospitalInformation> findAllList = hospitalInformationMapper.findAll(user);
		PageInfo<HospitalInformation> page = new PageInfo<HospitalInformation>(findAllList);
		return page;
	}

	@Override
	public List<HospitalInformation> findAll(User user) {
		List<HospitalInformation> findAllList = hospitalInformationMapper.findAll(user);
		return findAllList;
	}

	public void save(HospitalInformation hos) {
		hospitalInformationMapper.saveHospitalInfo(hos);
	}

	public boolean update(HospitalInformation hos) {
		return hospitalInformationMapper.updateHospitalInfo(hos);
	}

	public int validataOrg(String organizcode) {
		return hospitalInformationMapper.validataOrg(organizcode);
	}

	/**
	 * 获取全部机构类型
	 */
	@Override
	public List<Map<String, Object>> getAllOrganizationType() {
		List<Map<String, Object>> orgTypeList = hospitalInformationMapper.getAllOrganizationType();
		return orgTypeList;
	}

	/**
	 * 更新机构
	 */
	@Override
	public ResponseBean saveOrganization(HospitalInformation hospitalInformation) {
		// 转换机构拼音首字母
		hospitalInformation.setPyCode(ChinesePinyinUtil.getPinYinHeadChar(hospitalInformation.getOrganizName()));
		// 新增
		if (StringUtils.isBlank(hospitalInformation.getJGID())) {
			// 查询该机构代码是否系统注册
			HospitalInformation hosInfo = hospitalInformationMapper
					.getHosInfoByOrganizationCode(hospitalInformation.getOrganizCode());
			if (hosInfo != null) {
				return new ResponseBean(false, "该机构代码已注册!");
			}
			hospitalInformation.setJGID(hospitalInformation.getOrganizCode());
			hospitalInformationMapper.saveHospitalInfo(hospitalInformation);
		} else {
			// 更新
			hospitalInformationMapper.updateHospitalInfo(hospitalInformation);
		}
		return new ResponseBean();
	}

	/**
	 * 通过机构编码删除机构信息
	 */
	@Override
	public ResponseBean deleteOrganization(String organizCode) {
		// 判断当前机构下面是否有下级机构
		List<HospitalInformation> list = hospitalInformationMapper.getHosInfoByParentId(organizCode);
		if (list.size() > 0) {
			return new ResponseBean(false, "该机构下面还有下级机构,不能删除!");
		}
		hospitalInformationMapper.deleteOrganization(organizCode);
		return new ResponseBean();
	}

	/**
	 * 动态获取医院列表
	 */
	@Override
	public PagePojo getHospitalInfoByPage(Integer pageNo, Integer pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNo, pageSize);
		List<HospitalInformation> hosInfoList = hospitalInformationMapper.getHospitalInfoByPage(params);
		return PageUtil.ObjectPageInfo(hosInfoList);
	}

}
