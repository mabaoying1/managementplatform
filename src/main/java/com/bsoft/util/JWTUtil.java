package com.bsoft.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName: JWTUtil
 * @Description: token校验
 * @author mabaoying
 * @Copyright: Copyright (c) 2017
 * @date 2018年9月27日
 */
public class JWTUtil {

	/**
	 * 过期时间 毫秒
	 */
	private static final long EXPIRE_TIME = 7 * 24 * 3600 * 1000;

	/**
	 * 生成签名,5min后过期
	 * 
	 * @param source
	 * @param secret
	 * @return 加密的token
	 */
	public static String sign(String source, String secret) {

		try {
			// 过期时间
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			// 私钥及加密算法
			Algorithm algorithm = Algorithm.HMAC256(secret);
			// 设置头部信息
			Map<String, Object> header = new HashMap<>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			// 附带userName，userId生成签名
			return JWT.create().withHeader(header).withClaim("source", source).withExpiresAt(date).sign(algorithm);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 校验token是否正确
	 * 
	 * @param token
	 * @param secret
	 * @return 是否正确
	 */
	public static boolean verify(String token, String source, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("source", source).build();
			DecodedJWT jwt = verifier.verify(token);
			System.out.println("jwt====" + jwt + "------" + jwt.getExpiresAt());
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 * 
	 * @return token中包含的用户名
	 */
	public static String getSource(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("source").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String username = "zhangsan";
		String secret = "123456";
	
		  String token=sign(username,secret);
		 System.out.println("token====="+token);
		 
	//	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDMyNDY4NTAsInVzZXJJZCI6InpoYW5nc2FuIn0.1rzwBILnBJM07YzJnl8zrzwZDDy5CmT3gQgoB-5guy4";
		boolean flag = verify(token, username, secret);
		System.out.println("flag=====" + flag);

		System.out.println("username========" + getSource(token));
	}

}
