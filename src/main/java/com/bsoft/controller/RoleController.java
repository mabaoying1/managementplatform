package com.bsoft.controller;

import com.bsoft.model.Role;
import com.bsoft.service.RoleService;
import com.bsoft.util.ResponseBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mabaoying
 * @ClassName: RoleController
 * @Description: TODO
 * @date: 2019/10/22 17:33
 * @最后修改人:
 * @最后修改时间:
 */
@Controller
public class RoleController {


    @Autowired
    private RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * @Title: getRoleWithPage
     * @Description: 动态分页获取角色
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月12日
     * @return
     */
    @RequestMapping(value = "/getRoleWithPage")
    @ResponseBody
    public PageInfo<Role> getRoleWithPage(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "roleName", required = false) String roleName) {
        logger.info("动态分页获取角色");
        return roleService.getRoleWithPage(pageNo, pageSize, roleName);
    }

    /**
     * @Title: saveRole
     * @Description: 保存角色
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月12日
     * @return
     */
    @PostMapping(value = "/saveRole")
    @ResponseBody
    public ResponseBean saveRole(Role role) {

        if (null == role) {
            return new ResponseBean(false, "角色信息为null");
        }

        try {
            return roleService.saveRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(false, "操作异常!", e.getMessage());
        }
    }

    /**
     * @Title: deleteRole
     * @Description: 删除角色
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月12日
     * @return
     */
    @PostMapping(value = "/deleteRole")
    @ResponseBody
    public ResponseBean deleteRole(@RequestParam("id") String id) {
        if (StringUtils.isBlank(id)) {
            return new ResponseBean(false, "id不能为空!");
        }
        try {
            roleService.deleteRoleById(id);
            return new ResponseBean();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(false, "操作异常!", e.getMessage());
        }
    }

    /**
	 * @Title: getOfficeCodeByRoleId
	 * @Description: 通过角色获取科室code
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年12月31日
	 * @return
	 */
	@RequestMapping(value = "/getOfficeCodeByRoleId")
	@ResponseBody
	public String getOfficeCodeByRoleId(@RequestParam(value = "roleId") String roleId) {
		logger.info("通过角色获取科室code");
		String officdCode = roleService.getOfficeCodeByRoleId(roleId);
		return officdCode;
	}
	
    /**
	 * @Title: saveRoleOffice
	 * @Description: 保存角色科室
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年12月31日
	 * @return
	 */
	@RequestMapping(value = "/saveRoleOffice")
	@ResponseBody
	public ResponseBean saveRoleOffice(@RequestParam("roleId") String roleId,
			@RequestParam(value = "officeCodes") String officeCodes) {
		logger.info("保存角色科室");
		if (StringUtils.isBlank(roleId)) {
			return new ResponseBean(false, "参数indbId不能为空!");
		}
		try {
			roleService.saveRoleOffice(roleId, officeCodes);
		} catch (Exception e) {
			logger.error("保存角色科室异常", e);
			return new ResponseBean(false, "保存角色科室异常");
		}
		return new ResponseBean();
	}
}
