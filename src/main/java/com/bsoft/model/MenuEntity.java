package com.bsoft.model;

import java.io.Serializable;

/**
 * @author mabaoying
 * @ClassName: MenuEntity
 * @Description: 系统菜单实体类
 * @date 2019年03月15日
 * @最后修改人:
 * @最后修改时间:
 */
public class MenuEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9069982990776787050L;
	private String id;
	private String name;// 菜单名
	private String url;// 地址
	private String parentId;// 父id
	private String sort;// 排序
	private String icon;// 图标
	private String status;// 状态
	private String leaf;// 层级

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

}
