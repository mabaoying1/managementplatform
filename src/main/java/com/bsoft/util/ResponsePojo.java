package com.bsoft.util;

import java.io.Serializable;

/**
 * 返回值对象 既然想要实现restful，那我们要保证每次返回的格式都是相同的， 因此我建立了一个ResponseBean来统一返回的格式
 * 
 * @author mabaoying
 * @date 2019-03-14
 *
 */
@SuppressWarnings("javadoc")
public class ResponsePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;// http 状态码
	private String msg;// 返回信息
	private Object data;// 返回的数据

	public ResponsePojo() {
		super();
	}

	public ResponsePojo(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponsePojo(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
