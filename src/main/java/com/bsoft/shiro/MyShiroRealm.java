package com.bsoft.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bsoft.model.User;
import com.bsoft.service.UserService;
import com.bsoft.util.JWTUtil;

//实现AuthorizingRealm接口用户用户认证
@Component
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 大坑！，必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	// 角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户
		String username = JWTUtil.getSource(principals.toString());
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		if ("zhangsan".equals(username)) {
			simpleAuthorizationInfo.addRole("admin");
		}

		return simpleAuthorizationInfo;
	}

	// 用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		String token = (String) authenticationToken.getCredentials();
		// 解密获得userId，用于和数据库进行对比
		String userId = JWTUtil.getSource(token);
		if (userId == null) {
			throw new AuthenticationException("token无效!");
		}

		User user = userService.getUserByUserId(userId);
		if (null == user) {
			throw new AuthenticationException("用户不存在!");
		}

		if (!JWTUtil.verify(token, userId, user.getPassword())) {
			throw new AuthenticationException("用户名或密码错误!");
		}

		return new SimpleAuthenticationInfo(token, token, "my_realm");

	}

	public static void main(String[] args) {
		SimpleHash sh = new SimpleHash("MD5", "2009ms1234", null, 1);
		System.out.println(sh.toString());
		// 加密加盐算法
		/*
		 * Object sat = ByteSource.Util.bytes("admin"); SimpleHash sh2 = new
		 * SimpleHash("MD5", "123456", sat, 1);
		 * System.out.println(sh2.toString());
		 */
	}
}