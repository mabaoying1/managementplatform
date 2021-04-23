package com.bsoft.controller;

import com.alibaba.fastjson.JSONArray;
import com.bsoft.config.HospitalConfig;
import com.bsoft.constant.Constant;
import com.bsoft.constant.Parameter;
import com.bsoft.constant.Theme;
import com.bsoft.constant.WebArgs;
import com.bsoft.model.HospitalInformation;
import com.bsoft.model.ResHospital;
import com.bsoft.model.RoleOffice;
import com.bsoft.model.User;
import com.bsoft.rpc.DimRpc;
import com.bsoft.rpc.IndRpc;
import com.bsoft.service.IndbDimService;
import com.bsoft.service.RoleService;
import com.bsoft.service.ThemeService;
import com.bsoft.service.UserService;
import com.bsoft.util.DateUtil;
import com.bsoft.util.JWTUtil;
import com.bsoft.util.XmlToTheme;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @ClassName: DigitalHospitalController
 * @Description: 综管app 业务控制器
 * @date:
 * @最后修改人:
 * @最后修改时间:
 */
@Controller
public class DigitalHospitalController {

	@Autowired
	private ThemeService themeService;
	@Autowired
	private DimRpc dimRpc;
	@Autowired
	private IndbDimService indbDimService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private IndRpc indRpc;
	@Autowired
	private UserService userService;
	@Autowired
	private HospitalConfig resHospital;
	private static final Logger logger = LoggerFactory.getLogger(DigitalHospitalController.class);

	/**
	 * 获取医院当日总收入
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	// @RequiresAuthentication
	// @RequestMapping(value = "getDigitalHospitalIncome")
	@RequestMapping(value = "getBIDataAll")
	@ResponseBody
	public Object getDigitalHospitalIncome(@ModelAttribute WebArgs args, @ModelAttribute Parameter pa)
			throws Exception {
		String month = args.getDimMonth();
		if (!StringUtils.isBlank(month)) {
			Date today = new Date();
			try {
				today = DateUtil.formatString4Mon(month);
				args.setDimMonth("");
			} catch (ParseException e) {
				logger.error(e.getMessage() + "日期格式不合法");
			}
			args.setStartdate(DateUtil.getFirstDayOfMonth2Str(today));
			args.setEnddate(DateUtil.getLastDayOfMonth2Str(today));
		}

		// 获取主题
		Theme theme = XmlToTheme.getThemeByKey(args.getThemeID());
		if (null == theme) {
			throw new Exception("未找到相关主题！");
		}
		theme.putParam(pa);
		
		
		
		logger.info("theme=====" + theme.getTheme_key());
		// 处理科室维度 ，并按照角色权限
		String office = args.getDimDept();
 
		
		List result = Lists.newArrayList();
		if (StringUtils.isNotBlank(office)) {
			String[] offices = office.split(",");
			for (String dept : offices) {
				args.setDimDept(dept);
				theme.setArgs(args);
				List list = themeService.getThemeFormatData(theme);
				if (null != list && list.size() > 0)
					result.addAll(list);
			}
		} else {
			theme.setArgs(args);
			result = themeService.getThemeFormatData(theme);
		}
		// logger.info("list=====" + result);
		String strResult = JSONArray.toJSONString(result);
		Map<String, String> map = theme.getIndID2Key();
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			strResult = strResult.replace(key, map.get(key) + Constant.SEPARATOR);

		}
		Map<String, String> dimMap = theme.getSlumnDim();

		Iterator<String> dimResult = dimMap.keySet().iterator();
		while (dimResult.hasNext()) {
			String key = dimResult.next();
			strResult = strResult.replace(key, dimMap.get(key));
		}
		logger.info("list=====" + strResult);
		return strResult;

	}

	@RequestMapping(value = "flushTheme")
	@ResponseBody
	public Object flushTheme() throws JAXBException, IOException {
		try {
			return XmlToTheme.flushThemeList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "flushting...............................";
	}

	@RequestMapping(value = "getDim")
	@ResponseBody
	public Object getDim(String dicid) {
		return dimRpc.getMdrDimData(dicid);
	}

	@RequestMapping(value = "getOrgCode")
	@ResponseBody
	public Object getOrgCode(HttpServletRequest request) {
		String token = request.getHeader("token");
		String userid = JWTUtil.getSource(token);
		User user = userService.getUserByUserId(userid);
		List<HospitalInformation> hot = user.getHosList();
		List<Map<String, Object>> dim = dimRpc.getMdrDimData(resHospital.getOrgDim());
		List<Map<String, Object>> result = Lists.newArrayList();
		for (HospitalInformation info : hot) {

			for (int i = 0; i < dim.size(); i++) {
				Map<String, Object> map = dim.get(i);
				String code = (String) map.get("code");
				if (code.equals(info.getOrganizCode().trim()))
					result.add(map);
			}
		}
		return result;
	}

	@RequestMapping(value = "getOfficeCode")
	@ResponseBody
	public Object getOfficeCode(@ModelAttribute String officeType, HttpServletRequest request) {
		String token = request.getHeader("token");
		String userid = JWTUtil.getSource(token);
		// User user = userService.getUserByUserId(userid);
		RoleOffice role = roleService.getOfficeCodeByUserId(userid);
		RoleOffice hot = roleService.getOfficeCodeByUserId(userid);
		String dimKey = "";
		if (null != officeType && "cw".equals(officeType))
			dimKey = resHospital.getCwOfficeDim();
		else
			dimKey = resHospital.getYwOfficeDim();
		List<Map<String, Object>> dim = dimRpc.getMdrDimData(dimKey);
		List<Map<String, Object>> result = Lists.newArrayList();
		if (role.getRoleLevel() == 1) {
			return dim;
		} else if (role.getRoleLevel() >= 2) {
			for (String office : hot.getOfficesList()) {

				for (int i = 0; i < dim.size(); i++) {
					Map<String, Object> map = dim.get(i);
					String code = (String) map.get("code");
					if (code.equals(office))
						result.add(map);
				}
			}
		} 
	
		return result;
	}

	/**
	 * 按科室统计
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getBIData")
	@ResponseBody
	public Object getBIDataAll(@ModelAttribute WebArgs args, @ModelAttribute Parameter pa, HttpServletRequest request)
			throws Exception {

		// List<String> roles=roleService.getOfficeCodeByUserId(userid);

		// 处理查询时间中有月度数据的方法
		String month = args.getDimMonth();
		if (!StringUtils.isBlank(month)) {
			Date today = new Date();
			try {
				today = DateUtil.formatString4Mon(month);
				args.setDimMonth("");
			} catch (ParseException e) {
				logger.error(e.getMessage() + "日期格式不合法");
			}
			args.setStartdate(DateUtil.getFirstDayOfMonth2Str(today));
			args.setEnddate(DateUtil.getLastDayOfMonth2Str(today));
		}

		// 获取主题
		Theme theme = XmlToTheme.getThemeByKey(args.getThemeID());
		if (null == theme) {
			throw new Exception("未找到相关主题！");
		}

		// 处理科室维度 ，并按照角色权限
		String office = args.getDimDept();
		String token = request.getHeader("token");
		String userid = JWTUtil.getSource(token);

		RoleOffice role = roleService.getOfficeCodeByUserId(userid);
		String[] offices = null;
		if (null != office)
			offices = office.split(",");

		List<String> lOffices = Lists.newArrayList();
		if (role.getRoleLevel() == 1) {
		} else if (role.getRoleLevel() >= 2) {
			String[] roffices = role.getOfficesList();

			if (null != offices) {
				for (String of : offices) {
					for (String rf : roffices) {
						if (rf.equals(of))
							lOffices.add(of);
					}
				}

				offices = lOffices.toArray(offices);
			} else {
				offices = roffices;
			}
		}
		theme.putParam(pa);
		List result = Lists.newArrayList();
		if (null != offices && offices.length > 0) {
			for (String dept : lOffices) {
				args.setDimDept(dept);
				theme.setArgs(args);
				List list = themeService.getThemeFormatData(theme);
				if (null != list && list.size() > 0)
					result.addAll(list);
			}
		} else {
			theme.setArgs(args);
			result = themeService.getThemeFormatData(theme);
		}
		// logger.info("list=====" + result);
		String strResult = JSONArray.toJSONString(result);
		Map<String, String> map = theme.getIndID2Key();
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			strResult = strResult.replace(key, map.get(key) + Constant.SEPARATOR);

		}
		Map<String, String> dimMap = theme.getSlumnDim();

		Iterator<String> dimResult = dimMap.keySet().iterator();
		while (dimResult.hasNext()) {
			String key = dimResult.next();
			strResult = strResult.replace(key, dimMap.get(key));
		}
		logger.info("list=====" + strResult);
		return strResult;
	}

}
