package com.bsoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.service.PersonnelService;
import com.bsoft.util.PagePojo;
import com.bsoft.util.ResponseBean;

/**
 * @author mabaoying
 * @ClassName: PersonnelController
 * @Description: 用户管理控制层
 * @date 2019年03月12日
 * @最后修改人:
 * @最后修改时间:
 */
@Controller
public class PersonnelController {
	private static final Logger logger = LoggerFactory.getLogger(PersonnelController.class);

	@Autowired
	private PersonnelService personnelService;

	/**
	 * @Title: getPersonnelWithPage
	 * @Description: 动态分页获取人员列表
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月12日
	 * @return
	 */
	@RequestMapping(value = "/getPersonnelWithPage")
	@ResponseBody
	public PagePojo getPersonnelWithPage(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "personName", required = false) String personName,
			@RequestParam(value = "organizCode", required = false) String organizCode) {
		logger.info("动态分页获取人员列表");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("personName", personName);
		paramMap.put("organizCode", organizCode);
		return personnelService.getPersonnelWithPage(pageNo, pageSize, paramMap);
	}

	/**
	 * @Title: deletepersonnel
	 * @Description: 删除人员
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月25日
	 * @return
	 */
	@RequestMapping(value = "/deletePersonnel")
	@ResponseBody
	public ResponseBean deletePersonnel(@RequestParam("personId") String personId) {
		logger.info("删除人员");
		try {
			personnelService.deletePersonnel(personId);
			return null;
		} catch (Exception e) {
			logger.error("删除人员异常", e);
			return new ResponseBean(false, "操作异常");
		}
	}
}
