/**
 * 
 * @ClassName: PageUtil.java
 * @Description: 
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年7月18日
 */
package com.bsoft.util;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * @author mabaoying
 * @ClassName: PageUtil
 * @Description: 分页的封装
 * @date 2019年03月10日
 * @最后修改人:
 * @最后修改时间:
 */
public class PageUtil {

	public static PagePojo ObjectPageInfo(List<?> list) {
		// 封装bootstrap
		PagePojo page = new PagePojo();
		// 将数据放入pageInfo，pageInfo会对数据进行处理，这个是封装好的类，直接调用即可
		PageInfo<?> pageInfo = new PageInfo<>(list);
		page.setPage(pageInfo.getPageNum());
		page.setTotal(pageInfo.getTotal());
		page.setRows(list);
		return page;
	}

}
