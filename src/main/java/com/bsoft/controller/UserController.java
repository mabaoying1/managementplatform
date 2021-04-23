package com.bsoft.controller;

import com.bsoft.model.User;
import com.bsoft.model.UserRole;
import com.bsoft.service.UserService;
import com.bsoft.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author mabaoying
 * @ClassName: UserController
 * @Description: TODO
 * @date: 2019/10/22 16:51
 * @最后修改人:
 * @最后修改时间:
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * @Title: appLogin
     * @Description: app登录
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月24日
     * @return
     */
    @PostMapping(value = "/appLogin")
    @ResponseBody
    public ResponsePojo appLogin(@RequestParam("userName") String ygbh, @RequestParam("password") String password) {
        logger.info("app登录");
        if (StringUtils.isBlank(ygbh)) {
            return new ResponsePojo("4000", "userName不能为空!");
        }
        if (StringUtils.isBlank(password)) {
            return new ResponsePojo("4000", "password不能为空!");
        }
        try {
            // 通过用户名获取用户信息
            User user = userService.getUserByUserId(ygbh);
            if (null == user) {
                return new ResponsePojo("1", "用户名不存在");
            }
            password = MD5StringUtil.MD5Encode(user.getUserId() + password);
            String password1 = user.getPassword();
            if (!password.equals(password1)) {
                return new ResponsePojo("3", "密码错误");
            }
            if (user.getStatus() == 0) {// 判断是否禁用
                return new ResponsePojo("2", "用户禁用");
            }
			/*if (null == user.getRole()) {
				return new ResponsePojo("4", "没有授予角色!");
			}
			if (user.getHosList().isEmpty()) {
				return new ResponsePojo("4", "没有机构权限!");
			}*/
            // 获取菜单树
            //List<TreeBean> menuTreeList = permissionService.getMenuTreeByRoleId(user.getRole().getId());
            
            //密码暂时可以传到前端，或者clone 后再设置为空。因为采用了缓存处理，该处设置为空后，再次登录时缓存对象的password 为空
            //user.setPassword("");
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("user", user);
            result.put("token", JWTUtil.sign(ygbh, password1));// 生成签名
            //result.put("menuTreeList", menuTreeList);
            return new ResponsePojo("0", "成功", result);
        } catch (Exception e) {
            logger.error("登录异常", e);
            return new ResponsePojo("500", "登录异常", e.getMessage());
        }
    }


    /**
     * @Title: login
     * @Description: 管理端登录
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月24日
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponsePojo login(@RequestParam("userName") String userId, @RequestParam("password") String password) {
        logger.info("管理端登录");
        if (StringUtils.isBlank(userId)) {
            return new ResponsePojo("4000", "userName不能为空!");
        }
        if (StringUtils.isBlank(password)) {
            return new ResponsePojo("4000", "password不能为空!");
        }
        try {
            // 通过用户名获取用户信息
            User user = userService.getUserByUserId(userId);
            if (null == user || !MD5StringUtil.MD5Encode(user.getUserId() + password).equals(user.getPassword())) {
                return new ResponsePojo("4000", "用户名或密码错误");
            }
            if (user.getStatus() == 0) {// 判断是否禁用
                return new ResponsePojo("4000", "用户禁用");
            }
            if (null == user.getRole()) {
                return new ResponsePojo("4000", "没有授予角色!");
            }
            if (user.getHosList().isEmpty()) {
                return new ResponsePojo("4000", "没有机构权限!");
            }

            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("token", JWTUtil.sign(userId, user.getPassword()));// 生成签名
            user.setPassword("");
            result.put("user", user);
            return new ResponsePojo("200", "成功", result);
        } catch (Exception e) {
            logger.error("登录异常", e);
            return new ResponsePojo("500", "登录异常", e.getMessage());
        }
    }

    /**
     * @Title: userListMS
     * @Description: 用户列表动态分页查询
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月22日
     * @return
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    public PagePojo userList(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "personName", required = false) String personName,
                               @RequestParam(value = "organizCode", required = false) String organizCode) {
        logger.info("用户列表动态分页查询");
        try {
            User user = new User();
            user.setJgid(organizCode);
            user.setUserName(personName);
            return userService.findAllMS(pageNo, pageSize, user);
        } catch (Exception e) {
            logger.error("用户列表动态分页查询异常", e);
            return null;
        }
    }

    /**
     * @Title: saveUser
     * @Description: 保存用户信息
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年03月25日
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean saveUser(@RequestParam("userId") String userId, @RequestParam("password") String password,
                                 @RequestParam("status") Integer status, @RequestParam("flag") String flag) {
        logger.info("保存用户信息");
        try {

            User user = new User();
            user.setUserId(userId);
            String passwordMd5 = new SimpleHash("MD5", userId + password, null, 1).toString();
            user.setPassword(passwordMd5);
            user.setCreateTime(new Date());
            user.setStatus(status);
            // 0新增 1修改
            if ("0".equals(flag)) {
                return userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }
        } catch (Exception e) {
            logger.error("保存用户信息异常", e);
            return new ResponseBean(false, "保存用户信息异常");
        }
        return new ResponseBean();
    }


    @PostMapping(value = "/findUserMS")
    @ResponseBody
    public User findUserMS(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
        User users = new User();
        users.setUserId(user.getUserId());
        users = userService.findUserMS(user);
        return users;
    }

    @PostMapping(value = "/updateUserMS")
    @ResponseBody
    public ResponseBean updateUserMS(User user) {
        ResponseBean responseBean=new ResponseBean();
        try {
            boolean isUpdate = userService.update(user);
            if (isUpdate) {
                responseBean.setSuccess(true);
                responseBean.setMsg("修改成功！");
            } else {
                responseBean.setSuccess(false);
                responseBean.setMsg("修改失败！");
            }
        } catch (Exception e) {
            responseBean.setSuccess(false);
            responseBean.setMsg("修改失败！");
        }
        return responseBean;
    }

    @PostMapping(value = "/findUserRole")
    @ResponseBody
    public UserRole findUserRole(HttpServletRequest request, HttpServletResponse response, Model model, UserRole userRole) {

            UserRole userRoles = new UserRole();
            userRoles.setUserName(userRole.getUserName());
            return userService.findUserRole(userRoles);
    }

    @RequestMapping(value = "getUserRoleDic", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getUserRoleDic(){
        List<UserRole> roleInfo = userService.findUserRoleAll(null);
        List<Map<String, Object>> dicList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < roleInfo.size(); i++) {
            Map<String, Object> hosDic = new HashMap<String, Object>();
            hosDic.put("value", roleInfo.get(i).getRoleId());
            hosDic.put("label", roleInfo.get(i).getRoleName());
            dicList.add(hosDic);
        }
        return dicList;
    }
    /**
     * @Title: unauthorized
     * @Description: 401
     * @最后修改人: mabaoying
     * @最后修改时间: 2019年10月22日
     * @return
     */
    @RequestMapping(value = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponsePojo unauthorized() {
        return new ResponsePojo("401", "Unauthorized!");
    }
}
