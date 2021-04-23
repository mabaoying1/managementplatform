package com.bsoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.model.MenuEntity;
import com.bsoft.service.PermissionService;
import com.bsoft.util.ResponseBean;
import com.bsoft.util.TreeBean;

/**
 * @author mabaoying
 * @ClassName: PermissionController
 * @Description: 权限管理控制层
 * @date 2019年03月12日
 * @最后修改人:
 * @最后修改时间:
 */
@Controller
public class PermissionController {
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

	@Autowired
	private PermissionService rolePermissionService;

	/**
	 * @Title: getMenuTree
	 * @Description: 获取树形菜单
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月15日
	 * @return
	 */
	@RequestMapping(value = "/getMenuTree")
	@ResponseBody
	public List<TreeBean> getMenuTree() {
		logger.info("获取树形菜单");
		try {
			return rolePermissionService.getMenuTree();
		} catch (Exception e) {
			logger.error("获取树形菜单异常", e);
			return null;
		}
	}

	/**
	 * @Title: deleteMenu
	 * @Description: 删除菜单
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月18日
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean deleteMenu(@RequestParam("id") String id) {
		logger.info("删除菜单");
		try {
			rolePermissionService.deleteMenu(id);
			return new ResponseBean();
		} catch (Exception e) {
			logger.error("删除菜单异常", e);
			return new ResponseBean(false, "操作异常");
		}
	}

	/**
	 * @Title: saveMenu
	 * @Description: 保存菜单
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月18日
	 * @return
	 */
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean saveMenu(MenuEntity menu) {
		logger.info("保存菜单");
		System.out.println(menu);
		try {
			rolePermissionService.saveMenu(menu);
			return new ResponseBean(true, "成功", menu);
		} catch (Exception e) {
			logger.error("保存菜单异常", e);
			return new ResponseBean(false, "操作异常");
		}
	}

	/**
	 * @Title: getMenuTree
	 * @Description: 获取角色菜单
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月15日
	 * @return
	 */
	@RequestMapping(value = "/getRoleMenuTree")
	@ResponseBody
	public Map<String, Object> getRoleMenuTree(@RequestParam("roleId") String roleId) {
		logger.info("获取角色树形菜单");
		try {
			List<Map<String, Object>> roleMenuList = rolePermissionService.getRoleMenu(roleId);
			List<TreeBean> menuTreeList = rolePermissionService.getMenuTree();
			Map<String, Object> result = new HashMap<>();
			result.put("roleMenuList", roleMenuList);
			result.put("menuTreeList", menuTreeList);
			return result;
		} catch (Exception e) {
			logger.error("获取角色树形菜单异常", e);
			return null;
		}
	}

	/**
	 * @Title: saveRoleMenu
	 * @Description: 角色分配菜单
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年03月21日
	 * @return
	 */
	@RequestMapping(value = "/saveRoleMenu")
	@ResponseBody
	public ResponseBean saveRoleMenu(@RequestParam("roleId") String roleId,
			@RequestParam(value = "menuIds", required = false) String[] menuIds, HttpServletRequest request) {
		logger.info("角色分配菜单");
		if (StringUtils.isBlank(roleId)) {
			return new ResponseBean(false, "参数roleId不能为空!");
		}
		try {
			rolePermissionService.saveRoleMenu(roleId, menuIds);
			return new ResponseBean();
		} catch (Exception e) {
			logger.error("角色分配菜单异常", e);
			return new ResponseBean(false, "角色分配菜单异常");
		}
	}

}
