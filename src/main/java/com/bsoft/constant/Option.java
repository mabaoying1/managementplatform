package com.bsoft.constant;


import javax.xml.bind.annotation.XmlElement;
import com.bsoft.annotation.Object2MapField;
public class Option extends Transform{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929600853110028764L;
	private boolean line2ColOption=false;
	private String searchType;
	@Object2MapField
	private   OrderOption orderOption;
	@Object2MapField
	private PageOption  pageOption;
	private String  totalOption;
	private String filterOption;
	@XmlElement(name="orderOption")
	public OrderOption getOrderOption() {
		return orderOption;
	}

	public void setOrderOption(OrderOption orderOption) {
		this.orderOption = orderOption;
	}

	
	@XmlElement(name="pageOption")
	public PageOption getPageOption() {
		return pageOption;
	}

	public void setPageOption(PageOption pageOption) {
		this.pageOption = pageOption;
	}

	@XmlElement(name="line2ColOption")
	public boolean isLine2ColOption() {
		return line2ColOption;
	}

	public void setLine2ColOption(boolean line2ColOption) {
		this.line2ColOption = line2ColOption;
	}
	@XmlElement(name="searchType")
	public String getSearchType() {
		return searchType;
	}

	@XmlElement(name="totalOption")
	public String getTotalOption() {
		return totalOption;
	}

	public void setTotalOption(String totalOption) {
		this.totalOption = totalOption;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@XmlElement(name="filterOption")
	public String getFilterOption() {
		return filterOption;
	}

	public void setFilterOption(String filterOption) {
		this.filterOption = filterOption;
	}
}
