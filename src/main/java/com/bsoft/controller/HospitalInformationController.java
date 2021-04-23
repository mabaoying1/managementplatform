package com.bsoft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.model.HospitalInformation;
import com.bsoft.service.HospitalInformationService;
import com.bsoft.util.PagePojo;
import com.bsoft.util.ResponseBean;


@Controller
public class HospitalInformationController {

	private static final Logger logger = LoggerFactory.getLogger(HospitalInformationController.class);

	@Autowired
	private HospitalInformationService hospitalInformationService;

	/**
	 * @Title: getHospitalInfoByPage
	 * @Description: 动态获取医院列表
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年04月03日
	 * @return
	 */
	@RequestMapping(value = "/getHospitalInfoByPage")
	@ResponseBody
	public PagePojo getHospitalInfoByPage(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "organizCode", required = false) String organizCode,
			@RequestParam(value = "organizName", required = false) String organizName) {
		logger.info("动态获取医院列表");
		Map<String, Object> params = new HashMap<>();
		params.put("organizCode", organizCode);
		params.put("organizName", organizName);
		return hospitalInformationService.getHospitalInfoByPage(pageNo, pageSize, params);
	}

	@RequestMapping(value = "getHospitalDic", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getHospitalDic() {

		List<HospitalInformation> hosInfo = hospitalInformationService.findAll(null);
		List<Map<String, Object>> dicList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < hosInfo.size(); i++) {
			Map<String, Object> hosDic = new HashMap<String, Object>();
			hosDic.put("value", hosInfo.get(i).getJGID());
			hosDic.put("label", hosInfo.get(i).getOrganizName());
			dicList.add(hosDic);
		}
		return dicList;
	}

	/**
	 * @Title: getAllOrganizationType
	 * @Description: 获取全部机构类型字典
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月7日
	 * @return
	 */
	@RequestMapping(value = "/getAllOrganizationType")
	@ResponseBody
	public List<Map<String, Object>> getAllOrganizationType() {
		logger.info("获取全部机构类型字典");
		List<Map<String, Object>> orgTypeList = hospitalInformationService.getAllOrganizationType();
		return orgTypeList;
	}

	/**
	 * @Title: saveOrganization
	 * @Description: 更新机构
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月7日
	 * @return
	 */
	@RequestMapping(value = "/saveOrganization", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean saveOrganization(HospitalInformation hospitalInformation) {
		logger.info("更新机构");
		try {
			return hospitalInformationService.saveOrganization(hospitalInformation);
		} catch (Exception e) {
			logger.error("更新机构异常", e);
			return new ResponseBean(false, e.getMessage());
		}
	}

	/**
	 * @Title: deleteOrganization
	 * @Description: 删除机构
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月7日
	 * @return
	 */
	@RequestMapping(value = "/deleteOrganization", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean deleteOrganization(@RequestParam("organizCode") String organizCode) {
		logger.info("删除机构");
		if (StringUtils.isBlank(organizCode)) {
			return new ResponseBean(false, "organizCode不能为空!");
		}
		try {
			return hospitalInformationService.deleteOrganization(organizCode);
		} catch (Exception e) {
			logger.error("删除机构异常", e);
			return new ResponseBean(false, e.getMessage());
		}
	}
}
