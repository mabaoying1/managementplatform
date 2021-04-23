package com.bsoft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.model.OfficeInformation;
import com.bsoft.model.User;
import com.bsoft.service.OfficeInformationService;
import com.bsoft.util.PagePojo;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageInfo;

@Controller
public class OfficeInformationController {

	private static final Logger logger = LoggerFactory.getLogger(OfficeInformationController.class);

	@Autowired
	private OfficeInformationService officeInformationService;

	/**
	 * 获取家医人员情况列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOfficeInformation", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAll(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
		PrintWriter pw = null;
		String pageNo = request.getParameter("pageNo");
		pw = response.getWriter();
		String jgid = request.getParameter("jgid") != null && !"".equals(request.getParameter("jgid"))
				? request.getParameter("jgid") : "";
		// String queryName = request.getParameter("nickname") != null &&
		// !"".equals(request.getParameter("nickname"))?
		// request.getParameter("nickname"):"";
		Integer pageSize = 20;
		if (pageNo == null || "".equals(pageNo)) {
			pageNo = "1";
		}
		User user = new User();
		user.setJgid(jgid);
		// user.setNickname(queryName);
		PageInfo<OfficeInformation> pageInfo = officeInformationService.findAll(Integer.valueOf(pageNo), pageSize,
				user);
		Map<String,Object> map=new HashMap<>();
		map.put("officeInformationPageInfo",pageInfo);
	return map;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/saveSYSoff", method = RequestMethod.POST)
	public void save(HttpServletRequest request, Model model, HttpServletResponse response)
			throws UnsupportedEncodingException {
		PrintWriter pw = null;
		int code = 500;
		try {
			pw = response.getWriter();
			String officeCode = request.getParameter("officeCode");
			String officeName = request.getParameter("officeName");
			String organizCode = request.getParameter("organizCode");
			String organizType = request.getParameter("organizType");
			String parentId = request.getParameter("parentId");
			String address = request.getParameter("address");
			String outPatientClinic = request.getParameter("outPatientClinic");
			String medicalLab = request.getParameter("medicalLab");
			String hospitaldept = request.getParameter("hospitaldept");
			String hospitalArea = request.getParameter("hospitalArea");
			String mtmanage = request.getParameter("mtmanage");
			String ratedBed = request.getParameter("ratedBed");
			String telphone = request.getParameter("telphone");
			String email = request.getParameter("email");
			String plsx = request.getParameter("plsx");
			String ybdz = request.getParameter("ybdz");
			String ybdzmc = request.getParameter("ybdzmc");
			String logoff = request.getParameter("logoff");

			if (null == officeCode || "".equals(officeCode)) {
				pw.write("科室编码不能为空！");
			} else if (null == organizCode || "".equals(organizCode)) {
				pw.write("机构名称不能为空！");
			} else if (null == officeName || "".equals(officeName)) {
				pw.write("科室名不能！");
			} else {
				OfficeInformation off = new OfficeInformation();

				int count = officeInformationService.validataOff(organizCode, officeName);
				if (count > 0) {
					officeInformationService.update(off);
				} else {
					officeInformationService.save(off);
				}
				pw.write("success");
				pw.flush();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
			if (pw != null) {
				pw.write(e.toString());
				pw.flush();
			}
		} catch (IOException e) {
			logger.error(e.toString(), e);
			e.printStackTrace();
			if (pw != null) {
				pw.write(e.toString());
				pw.flush();
			}
		}
	}

	/**
	 * @Title: getDepartmentPage
	 * @Description: 动态分页获取科室信息
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月8日
	 * @return
	 * 
	 */
	@RequestMapping(value = "/getDepartmentByPage")
	@ResponseBody
	public PagePojo getDepartmentByPage(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "loginUser", required = false) String userId,
			@RequestParam("organizCode") String organizCode,
			@RequestParam(value = "officeName", required = false) String officeName) {
		logger.info("动态分页获取科室信息");
		Map<String, Object> params = new HashMap<>();
		params.put("organizCode", organizCode);
		params.put("officeName", officeName);
		return officeInformationService.getDepartmentByPage(pageNo, pageSize, params);
	}

	/**
	 * @Title: getDepartmentPage
	 * @Description: 通过机构id获取科室信息
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月11日
	 * @return
	 * 
	 */
	@RequestMapping(value = "/getDepartmentByOrganizCode")
	@ResponseBody
	public List<Map<String, Object>> getDepartmentByOrganizCode(@RequestParam("organizCode") String organizCode) {
		List<OfficeInformation> offInfoList = officeInformationService.getDepartmentByOrganizCode(organizCode);
		List<Map<String, Object>> dicList = new ArrayList<Map<String, Object>>();
		Map<String, Object> orgTypeDic;
		for (OfficeInformation offInfo : offInfoList) {
			orgTypeDic = new HashMap<String, Object>();
			orgTypeDic.put("value", offInfo.getOfficeCode());
			orgTypeDic.put("label", offInfo.getOfficeName());
			dicList.add(orgTypeDic);
		}
		return dicList;
	}

	/**
	 * @Title: saveDepartment
	 * @Description: 更新科室信息
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月8日
	 * @return
	 */
	@RequestMapping(value = "/saveDepartment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean saveDepartment(OfficeInformation officeInformation) {
		try {
			return officeInformationService.saveDepartment(officeInformation);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseBean(false, e.getMessage());
		}
	}

	/**
	 * @Title: deleteOffice
	 * @Description: 删除科室
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月8日
	 * @return
	 */
	@RequestMapping(value = "/deleteOffice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean deleteOrganization(@RequestParam("id") String id) {
		if (StringUtils.isBlank(id)) {
			return new ResponseBean(false, "id不能为空!");
		}
		try {
			return officeInformationService.deleteOrganization(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseBean(false, e.getMessage());
		}
	}

}
