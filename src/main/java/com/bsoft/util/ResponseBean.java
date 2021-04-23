package com.bsoft.util;

import java.io.Serializable;

/**
 * @author mabaoying
 * @ClassName: ResponseBean
 * @Description: 数据响应实体类
 * @date 2019年10月22日
 * @最后修改人:
 * @最后修改时间:
 */
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = -7453271120250807424L;

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object data = null;// 其他信息

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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ResponseBean(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public ResponseBean(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public ResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
