package com.bsoft.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bsoft.model.MenuEntity;

/**
 * 树形菜单Bean
 * 
 * @author mabaoying
 * @time 2019-03-15
 */

public class TreeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String label;
	private String pid;
	private String data;
	private String state;
	private boolean checked;
	private String key;
	private List<TreeBean> children = new ArrayList<TreeBean>();

	public TreeBean() {
		super();
	}

	public TreeBean(Object[] obj) {
		super();
		this.id = obj[0] == null ? "" : obj[0].toString();
		this.label = obj[1] == null ? "" : obj[1].toString();
		this.pid = obj[2] == null ? "" : obj[2].toString();
		String url = obj[3] == null ? "" : obj[3].toString();
		String ioc = obj[4] == null ? "" : obj[4].toString();
		String leaf = obj[5] == null ? "" : obj[5].toString();
		String key = obj[6] == null ? "" : obj[6].toString();
		String px = obj[7] == null ? "" : obj[7].toString();
		String status = obj[8] == null ? "" : obj[8].toString();
		this.data = url + "|*|" + ioc + "|*|" + px + "|*|" + status + "|*|" + leaf;
		this.key = key;
		this.state = "open";
		this.checked = false;
	}

	public TreeBean(MenuEntity menu) {
		super();
		this.id = menu.getId() == null ? "" : menu.getId().toString();
		this.label = menu.getName() == null ? "" : menu.getName();
		this.pid = menu.getParentId() == null ? "" : menu.getParentId();
		String status = menu.getStatus() == null ? "" : menu.getStatus();
		String url = menu.getUrl() == null ? "" : menu.getUrl();
		String ioc = menu.getIcon() == null ? "" : menu.getIcon();
		String leaf = menu.getLeaf() == null ? "" : menu.getLeaf();
		String key = menu.getId() == null ? "" : menu.getId();
		String px = menu.getSort() == null ? "" : menu.getSort();
		this.data = url + "|*|" + ioc + "|*|" + px + "|*|" + status + "|*|" + leaf;
		this.key = key;
		this.state = "open";
		this.checked = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<TreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}

}