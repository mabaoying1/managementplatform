package com.bsoft.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.bsoft.constant.Arg;
import com.bsoft.constant.Constant;
import com.bsoft.constant.IndbNames;
import com.bsoft.constant.Option;
import com.bsoft.constant.Theme;
import com.bsoft.rpc.DimRpc;
import com.bsoft.rpc.IndRpc;
import com.bsoft.service.ThemeService;
import com.bsoft.util.DBObjHelper;
import com.bsoft.util.ThematicDataComparator;
import com.bsoft.util.XmlToTheme;
import com.google.common.collect.Maps;


@Service
@Transactional
public class ThemeServiceImpl implements ThemeService {
	private static final Logger logger = LoggerFactory.getLogger(ThemeServiceImpl.class);

	@Autowired
	private IndRpc indRpc;
	@Autowired
	private DimRpc dimRpc;

	@Override
	public List<Map<String, Object>> getThemeListData(Theme theme) {
		return indRpc.queryIndDataWithMutilConditions(theme.toIndIdList(), theme.toIndsMap(), theme.toSlumnList(),
				theme.getArgList(), theme.getOption().toMap());
	}

	@Override
	public Map<String, Object> getThemeMapData(Theme theme) {
		List<Map<String, Object>> list = indRpc.queryIndDataWithMutilConditions(theme.toIndIdList(), theme.toIndsMap(), theme.toSlumnList(),
				theme.getArgList(), theme.getOption().toMap());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Object getThemeData(Theme theme) {
		return indRpc.queryIndDataWithMutilConditions(theme.toIndIdList(), theme.toIndsMap(), theme.toSlumnList(),
				theme.getArgList(), theme.getOption().toMap());
	}

	/**
	 * 返回格式化处理后的数据
	 */
	@Override
	public String getTheme2String(Theme theme) {

		// 指标参数
		List<String> indIdList = theme.toIndIdList();
		Map<String, Map<String, Object>> indsMap = theme.toIndsMap();
		List<String> slumnList = theme.toSlumnList();
		List<Map<String, Object>> argList = theme.getArgList();
		Map<String, Object> optionMap = theme.getOptionMap();
		logger.info(argList.toString());
		List<Map<String, Object>> list = indRpc.queryIndDataWithMutilConditions(indIdList, indsMap, slumnList, argList,
				optionMap);
		logger.info("返回数据" + list.toString());
		if (null != list && list.size() > 0) {
			// formatData(list, theme.toIndItemsList(), slumnList, null,
			// optionMap);
			formatData(theme, list);
		}
		
		
	String strResult=	JSONArray.toJSONString(list);
	Map<String, String> map=theme.getIndID2Key();
	Iterator<String> result= map .keySet().iterator();
		while (result.hasNext()) {
		   String key=	result.next();
		   strResult=	strResult.replace(key, map.get(key)+Constant.SEPARATOR);
			
		}
		Map<String, String> dimMap=theme.getSlumnDim();
		
		Iterator<String> dimResult= dimMap .keySet().iterator();
		while (dimResult.hasNext()) {
		   String key=	dimResult.next();
		   strResult=	strResult.replace(key, dimMap.get(key));
			
		}
		return strResult;
	}

	/**
	 * 返回格式化处理后的数据
	 */
	@Override
	public List<Map<String, Object>> getThemeFormatData(Theme theme ) {
		// 指标参数
		List<String> indIdList = theme.toIndIdList();
		Map indsMap = theme.toIndsMap();
		List<String> dimIdList = theme.toSlumnList();
		List argList = theme.getArgList();
		Map optionMap = theme.getOptionMap();
	//	logger.info("参数》》》》》》》》》》》》》"+argList.toString());
		List<Map<String, Object>> list = indRpc.queryIndDataWithMutilConditions(indIdList, indsMap, dimIdList, argList,
				optionMap);
		//logger.info("返回数据" + list.toString());
		
		if (null != list && list.size() > 0) {
			Option op =theme.getOption();
			String searchType=op.getSearchType();
			if(StringUtils.isNotBlank(searchType)){
				Map resultMap=Maps.newHashMap();
				for(Map map:list){
					resultMap.putAll(map);
				}
				list.clear();
				list.add(resultMap);
			}
			formatData(theme, list);
			// 数据排序 默认按照第一维度进行排序
			orderData(list,dimIdList);
			return list;
		}
		return null;
	}
	
	public void orderData(List<Map<String,Object>> datalist,List<String> dimList){
			String orderfield = "";
			String order = "asc";
			
			ThematicDataComparator tdc = new ThematicDataComparator();
			if("".equals(orderfield)){
				tdc.useNumberCompare = false;
				orderfield = dimList.get(0);
				String notDate = "";
				//如果第一个维度是时间，先按时间排序在按第二个字段排序
				if(Integer.parseInt(orderfield.replace("DIM", ""))<=5){
					for (String dim : dimList) {
						if(Integer.parseInt(dim.replace("DIM", ""))>5){
							notDate=dim;
							break;
						}
					}
					if(!"".equals(notDate)){
						tdc.fIsDate=true;
						orderfield=orderfield+","+notDate+"_DIMORDER";
					}
				}else{
					orderfield =orderfield+"_DIMORDER";
					tdc.useNumberCompare = true;
				}
			}
			tdc.field = orderfield;
			tdc.orderby = order;
			Collections.sort(datalist, tdc);
	}

	public List<Map<String, Object>> formatData(Theme theme, List<Map<String, Object>> datalist) {
		Option option = theme.getOption();
		String filterOption = option.getFilterOption(); // 数据过滤条件
		// 清除数据中的key包含$的的entry
		remove$entry(datalist);
		// 行转列的数据过滤是不一样的
		if (option.isLine2ColOption()) {
			if (Constant.FILTER_OPTION_COLUMN.equals(filterOption)) {
				delRowData(datalist, null);
			} else if (Constant.FILTER_OPTION_LINE.equals(filterOption)) {
				delColData(datalist, theme.toIndItemsList(), null);
			} else if (Constant.FILTER_OPTION_MUTIL.equals(filterOption)) {
				delRowData(datalist, null);
				delColData(datalist, theme.toIndItemsList(), null);
			}
		} else {
			if (Constant.FILTER_OPTION_COLUMN.equals(filterOption)) {
				delColData(datalist, theme.toIndItemsList(), null);
			} else if (Constant.FILTER_OPTION_LINE.equals(filterOption)) {
				delRowData(datalist, null);
			} else if (Constant.FILTER_OPTION_MUTIL.equals(filterOption)) {
				delRowData(datalist, null);
				delColData(datalist, theme.toIndItemsList(), null);
			}
		}

		Map<String, Map<String, String>> dicMaps = Maps.newHashMap();
		for (String dicId : theme.toSlumnList()) {
			String dd = dicId.replace(Constant.DIMENSION_PREFIX, "");
			int dicnum = Integer.parseInt(dd);
			if (dicnum > 5) {// 非时间维度
				List<Map<String, Object>> dicList = dimRpc.getMdrDimData(dicId);
				Map<String, String> dicMap = Maps.newHashMap();
				for (Map<String, Object> m : dicList) {
					dicMap.put(m.get("code").toString(), m.get("text").toString());
					dicMap.put(m.get("code") + "_DIMORDER",
							m.get("dimorder") != null ? m.get("dimorder").toString() : "0");
				}
				dicMaps.put(dicId, dicMap);
			}
		}

		Map<String, Map<String, Object>> indMaps = Maps.newHashMap();
		for (Map<String, Object> m : theme.toIndItemsList()) {
			indMaps.put(m.get("indId").toString(), m);
		}

		if (datalist != null) {
			// 记录聚集函数的数据
			Map<String, Object> gatherMap = Maps.newHashMap();

			for (int i = 0; i < datalist.size(); i++) {
				Map<String, Object> megerDataItem = Maps.newHashMap();
				for (Map.Entry<String, Object> entry : datalist.get(i).entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue() == null ? (key.indexOf(Constant.INDICATOR_PREFIX) != -1 ? "0" : "")
							: entry.getValue();
					if (key.indexOf(Constant.DIMENSION_PREFIX) != -1 && dicMaps.containsKey(key)) {// 非时间维度
						String dicText = dicMaps.get(key).get(value) == null ? value.toString()
								: dicMaps.get(key).get(value);
						megerDataItem.put(key + "_Text", dicText);
						megerDataItem.put(key + "_DIMORDER", dicMaps.get(key).get(value + "_DIMORDER"));
					} else if (key.indexOf(Constant.DIMENSION_PREFIX) != -1 && key.indexOf(Constant.SEPARATOR) == -1) { // 时间维度
						megerDataItem.put(key + "_Text", value);
					} else if (key.indexOf(Constant.INDICATOR_PREFIX) != -1 && key.indexOf("_SUM") != -1) {// 处理指标合计数据
						Map<String, Object> indMap = indMaps.get(key.substring(0, 10));
						if (indMap == null)
							continue;
						Object v = formatIndData(indMap, value == "" ? "0" : value);
						megerDataItem.put(key, v);
					} else if (key.indexOf(Constant.INDICATOR_PREFIX) != -1) {// 指标数据格式化
						Map<String, Object> indMap = indMaps.get(key);
						indMap = indMap == null ? indMaps.get(key.substring(0, 10)) : indMap;
						if (indMap == null)// 返回的数据中会包含一些关联基础指标的数据，无需处理
							continue;
						Object v = formatIndData(indMap, value);
						megerDataItem.put(key, v);
					}
				}
				datalist.get(i).putAll(megerDataItem);
			}
		}

		return datalist;
	}

	/**
	 * 
	 * @param 待处理数据
	 * @param 指标集
	 * @param 维度
	 * @param 需隐藏列
	 * @param 分页排序操作对象
	 */
	public void formatData(List<Map<String, Object>> datalist, List<Map<String, Object>> indList, List<String> dimList,
			List<String> hideCoulumn, Map<String, Object> option) {
		String filterOption = (String) option.get("filterOption"); // 数据过滤条件
		Boolean line2Col = (Boolean) option.get("line2ColOption");
		// 清除数据中的key包含$的的entry
		remove$entry(datalist);
		// 行转列的数据过滤是不一样的
		if (line2Col) {
			if ("column_0".equals(filterOption)) {
				delRowData(datalist, hideCoulumn);
			} else if ("line_0".equals(filterOption)) {
				delColData(datalist, indList, null);
			} else if ("mutil_0".equals(filterOption)) {
				delRowData(datalist, hideCoulumn);
				delColData(datalist, indList, null);
			}
		} else {
			if ("column_0".equals(filterOption)) {
				delColData(datalist, indList, hideCoulumn);
			} else if ("line_0".equals(filterOption)) {
				delRowData(datalist, null);
			} else if ("mutil_0".equals(filterOption)) {
				delRowData(datalist, null);
				delColData(datalist, indList, hideCoulumn);
			}
		}

		Map<String, Map<String, String>> dicMaps = Maps.newHashMap();
		for (String dicId : dimList) {
			String dd = dicId.replace(Constant.DIMENSION_PREFIX, "");
			int dicnum = Integer.parseInt(dd);
			if (dicnum > 5) {// 非时间维度
				List<Map<String, Object>> dicList = dimRpc.getMdrDimData(dicId);
				Map<String, String> dicMap = Maps.newHashMap();
				for (Map<String, Object> m : dicList) {
					dicMap.put(m.get("code").toString(), m.get("text").toString());
					dicMap.put(m.get("code") + "_DIMORDER",
							m.get("dimorder") != null ? m.get("dimorder").toString() : "0");
				}
				dicMaps.put(dicId, dicMap);
			}
		}

		Map<String, Map<String, Object>> indMaps = Maps.newHashMap();
		for (Map<String, Object> m : indList) {
			indMaps.put(m.get("indId").toString(), m);
		}

		if (datalist != null) {
			// 记录聚集函数的数据
			Map<String, Object> gatherMap = Maps.newHashMap();

			for (int i = 0; i < datalist.size(); i++) {
				Map<String, Object> megerDataItem = Maps.newHashMap();
				for (Map.Entry<String, Object> entry : datalist.get(i).entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue() == null ? (key.indexOf(Constant.INDICATOR_PREFIX) != -1 ? "0" : "")
							: entry.getValue();
					if (key.indexOf(Constant.DIMENSION_PREFIX) != -1 && dicMaps.containsKey(key)) {// 非时间维度
						String dicText = dicMaps.get(key).get(value) == null ? value.toString()
								: dicMaps.get(key).get(value);
						megerDataItem.put(key + "_Text", dicText);
						megerDataItem.put(key + "_DIMORDER", dicMaps.get(key).get(value + "_DIMORDER"));
					} else if (key.indexOf(Constant.DIMENSION_PREFIX) != -1 && key.indexOf(Constant.SEPARATOR) == -1) { // 时间维度
						megerDataItem.put(key + "_Text", value);
					} else if (key.indexOf(Constant.INDICATOR_PREFIX) != -1 && key.indexOf("_SUM") != -1) {// 处理指标合计数据
						Map<String, Object> indMap = indMaps.get(key.substring(0, 10));
						if (indMap == null)
							continue;
						Object v = formatIndData(indMap, value == "" ? "0" : value);
						megerDataItem.put(key, v);

						// if(!gatherMap.containsKey(key+"_SUM")){//计算聚集函数数据
						// gatherMap.put(key+"_SUM", v);
						// gatherMap.put(key+"_max", v);
						// gatherMap.put(key+"_min", v);
						// }else{
						// double d_sum =
						// Double.parseDouble(gatherMap.get(key+"_SUM").toString())+Double.parseDouble(v.toString());
						// int decimalLengthf =
						// Integer.parseInt(indMap.get("decimalLength").toString());
						//// Object v_sum = formatIndData(indMap,d_sum);
						// Object v_sum =
						// dataFormat(d_sum,"number",decimalLengthf);
						// gatherMap.put(key+"_SUM", v_sum);
						// if(Double.parseDouble(v.toString())>Double.parseDouble(gatherMap.get(key+"_max").toString())){
						//// Object v_max = formatIndData(indMap,v);
						// gatherMap.put(key+"_max", v);
						// }
						// if(Double.parseDouble(v.toString())<Double.parseDouble(gatherMap.get(key+"_min").toString())){
						//// Object v_min = formatIndData(indMap,v);
						// gatherMap.put(key+"_min", v);
						// }
						// }

					} else if (key.indexOf(Constant.INDICATOR_PREFIX) != -1) {// 指标数据格式化
						Map<String, Object> indMap = indMaps.get(key);
						indMap = indMap == null ? indMaps.get(key.substring(0, 10)) : indMap;
						if (indMap == null)// 返回的数据中会包含一些关联基础指标的数据，无需处理
							continue;
						Object v = formatIndData(indMap, value);
						megerDataItem.put(key, v);
					}
				}
				datalist.get(i).putAll(megerDataItem);
			}
		}

	}

	public String formatIndData(Map<String, Object> indMap, Object value) {

		String indUnit = indMap.get("indUnit").toString();
		int decimalLengthf = Integer.parseInt(indMap.get("decimalLength").toString());

		Double dv = Double.parseDouble(value.toString());
		Object realv = 0;
		switch (indUnit) {
		case "percentage":
			dv = dv * 100;
			break;
		case "permillage":
			dv = dv * 1000;
			break;
		case "tenThousand":
			dv = dv / 10000;
			break;
		case "million":
			dv = dv / 1000000;
			break;
		case "tenMillion":
			dv = dv / 10000000;
			break;
		case "billion":
			dv = dv / 100000000;
			break;
		default:
			break;
		}

		if (decimalLengthf == 0) {
			if (("").equals(indUnit) || ("empty").equals(indUnit)) {
				realv = dataFormat(dv, "int", 0);
			} else {
				// realv = dataFormat(dv,"number",3);
				realv = dataFormat(dv, "number", 0);
			}
		} else {
			realv = dataFormat(dv, "number", decimalLengthf);
		}
		return realv.toString();
	}

	/**
	 * 数字格式化
	 * 
	 * @param value
	 *            数字
	 * @param dataType
	 *            类型 int number
	 * @param dataLength
	 *            小数位数
	 * @return
	 */
	public Object dataFormat(Object value, String dataType, int dataLength) {
		if (value instanceof Double) {
			double vd = Double.parseDouble(String.valueOf(value));
			if (Double.isInfinite(vd)) {
				return Double.POSITIVE_INFINITY;
			} else if (Double.isNaN(vd)) {
				return Double.NaN;
			}
		}

		Object v = null;
		if ("int".equals(dataType)) {
			if (value == null) {
				v = 0;
			} else {
				v = new BigDecimal(DBObjHelper.dbObjToString(value)).setScale(0, BigDecimal.ROUND_HALF_UP);
			}
		} else if ("number".equals(dataType)) {
			double vd = Double.parseDouble(String.valueOf(value));
			v = new BigDecimal(DBObjHelper.dbObjToString(vd)).setScale(dataLength, BigDecimal.ROUND_HALF_UP);
		} else {
			v = value;
		}
		return v;
	}

	/**
	 * 清除数据中的key包含$的的entry
	 * 
	 * @param datalist
	 */
	private void remove$entry(List<Map<String, Object>> datalist) {
		if (datalist != null && datalist.size() != 0) {
			for (int i = datalist.size() - 1; i >= 0; i--) {
				Map<String, Object> map = datalist.get(i);

				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String name = it.next();
					if (name.indexOf("$") != -1) {
						it.remove();

					} else {

					}
				}
			}
		}
	}

	private List<Map<String, Object>> remove$ByTheme(List<Map<String, Object>> datalist, Theme theme) {
		if (datalist != null && datalist.size() != 0) {
			for (int i = datalist.size() - 1; i >= 0; i--) {
				Map<String, Object> map = datalist.get(i);

				Iterator<String> it = map.keySet().iterator();
				while (it.hasNext()) {
					String name = it.next();
					if (name.indexOf("$") != -1) {
						it.remove();

					} else {

					}
				}
			}
		}
		return datalist;
	}

	/**
	 * 删除数组元素中的指定的指标数据
	 * 
	 * @param datalist
	 * @param indList
	 */
	private void delColData(List<Map<String, Object>> datalist, List<Map<String, Object>> indList,
			List<String> hideColumn) {
		for (Map<String, Object> m : indList) {
			String indid = m.get("indId").toString();
			boolean del = true;
			for (Map<String, Object> data : datalist) {
				if (new BigDecimal(data.get(indid).toString()).compareTo(BigDecimal.ZERO) != 0) {
					del = false;
					break;
				}
			}
			if (del == true) {
				for (Map<String, Object> data : datalist) {
					data.remove(indid);
					if (hideColumn != null && hideColumn.contains(indid + "_Text") == false) {
						hideColumn.add(indid + "_Text");
					}
				}
			}
		}
	}

	/**
	 * 直接删除数组元素中的一个元素
	 * 
	 * @param datalist
	 */
	private void delRowData(List<Map<String, Object>> datalist, List<String> hideColumn) {
		for (int i = datalist.size() - 1; i >= 0; i--) {
			Set<String> keys = datalist.get(i).keySet();
			boolean del = true;
			for (String key : keys) {
				if (key.indexOf(Constant.INDICATOR_PREFIX) != -1 && key.indexOf(Constant.SEPARATOR) == -1) {
					Object v = datalist.get(i).get(key);
					if (new BigDecimal(v.toString()).compareTo(BigDecimal.ZERO) != 0) {
						del = false;
						break;
					}
				}
			}
			if (del == true) {
				Set<String> allKey = datalist.get(i).keySet();
				for (String key : allKey) {
					if (key.indexOf(Constant.DIMENSION_PREFIX) != -1) {
						String value = (String) datalist.get(i).get(key);
						if (hideColumn != null && hideColumn.contains(key + Constant.SEPARATOR + value) == false) {
							hideColumn.add(key + Constant.SEPARATOR + value);
						}
					}
				}
				datalist.remove(i);
			}
		}
	}

}
