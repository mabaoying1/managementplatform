package com.bsoft.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author mabaoying
 * @ClassName: PagePojo
 * @Description: 分页的封装
 * @date 2019年03月10日
 * @最后修改人:
 * @最后修改时间:
 */
public class PagePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer page;
	private Long total;
	private List<?> rows;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}