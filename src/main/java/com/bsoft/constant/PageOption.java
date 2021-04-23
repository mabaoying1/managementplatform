package com.bsoft.constant;

import javax.xml.bind.annotation.XmlElement;

public class PageOption extends Transform {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1180254914558170875L;

	private  Integer pageSize=1;

	private  Integer currentPage=1;

	@XmlElement(name="pageSize")
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@XmlElement(name="currentPage")
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
