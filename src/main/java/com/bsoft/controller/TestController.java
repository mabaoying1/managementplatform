package com.bsoft.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsoft.rpc.IndRpc;
import com.bsoft.util.ResponsePojo;
import com.google.common.collect.Maps;

/**
 * @author mabaoying
 * @ClassName: TestController
 * @Description: 测试控制层
 * @date 2019年10月22日
 * @最后修改人:
 * @最后修改时间:
 */
@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private IndRpc indRpc;
	
	/**
	 * @Title: test
	 * @Description: 测试
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年10月22日
	 * @return
	 */
	@RequestMapping(value = "/test1")
	@ResponseBody
	public ResponsePojo test1(HttpSession session) {
		logger.info("测试1");
		try {
			session.setAttribute("user", "张三");
			return new ResponsePojo("200", "测试1成功", session.getAttribute("user"));
		} catch (Exception e) {
			logger.error("测试异常1", e);
			return new ResponsePojo("500", "测试1异常", e.getMessage());
		}
	}

	/**
	 * @Title: test
	 * @Description: 测试2
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年10月22日
	 * @return
	 */
	@RequestMapping(value = "/test2")
	@RequiresAuthentication
	@ResponseBody
	public ResponsePojo test2(HttpSession session) {
		logger.info("测试2");
		try {
			return new ResponsePojo("200", "测试2成功", session.getAttribute("user"));
		} catch (Exception e) {
			logger.error("测试异常", e);
			return new ResponsePojo("500", "测试2异常", e.getMessage());
		}
	}

	/**
	 * @Title: requireRole
	 * @Description: 需要admin角色才能访问
	 * @最后修改人: mabaoying
	 * @最后修改时间: 2019年10月22日
	 * @return
	 */
	@RequestMapping(value = "requireRole")
	@RequiresRoles("admin")
	@ResponseBody
	public ResponsePojo requireRole() {
		return new ResponsePojo("200", "You are visiting require_role", null);
	}
	
	@RequestMapping(value="/testRpc1")
	@ResponseBody
	public List<Map<String,Object>> testRpc1(){
		//所有的指标id
    	List<String> indidList =  new ArrayList<>();
    	indidList.add("INDB001762");
    	//要查询的维度，就是group by中的维度
    	List<String> column=new ArrayList<>();
    	column.add("DIM0000001");
    	column.add("dim0000584");
    	column.add("DIM0000282");
    	column.add("DIM0000003");
    	//对条件进行处理，适合mdr进行数据查询
    	Map<String, Object> args1=new HashMap<>();
    	args1.put("STARTDATE", "2019-01-01");
    	args1.put("ENDDATE", "2019-01-02");
    	List<Map<String,Object>> list=indRpc.getIndData(indidList,column,args1,null);
    	System.out.println(list);
    	return list;
	}
	
	/**
	 *  获取医院当日总收入
	 * @return
	 */
	@RequestMapping(value="/testRpc2")
	@ResponseBody
	public Map<String,Object> getDigitalHospitalIncome(){
		Long start=System.currentTimeMillis();
		//所有的指标id
		List<String> indidList =  new ArrayList<>();
		indidList.add("INDB001849");
		indidList.add("INDB001850");

		//要查询的维度，就是group by中的维度
		List<String> column=new ArrayList<>();
		column.add("DIM0000001");
		//	column.add("DIM0000787");
		//column.add("DIM0000282");
		//column.add("DIM0000003");

		//对条件进行处理，适合mdr进行数据查询
		Map<String, Object> args1=new HashMap<>();
		//args1.put("STARTDATE", "2019-09-01");
		//args1.put("ENDDATE", "2019-09-10");
		args1.put("DIM0000001", "2019-01-01");
		Map<String,Object> result=Maps.newHashMap();

		List<Map<String,Object>> list= indRpc.getIndData(indidList,column,args1,null);
		if(list!=null && list.size()>0){
			BigDecimal num1 = (BigDecimal)list.get(0).get("INDB001849");
			BigDecimal num2 = (BigDecimal)list.get(0).get("INDB001850");
			result.put("totalIncomeToday", num1.add(num2));
		}else{
			result.put("totalIncomeToday", 0);
		}

		System.out.println(list);
		System.out.println(result);
		long end=System.currentTimeMillis();
		System.out.println("所需时间======="+(end-start)+"毫秒");
		return result;
	}
	
	/**
	 *  今日门急诊量（合计）
	 * @return
	 */
	@RequestMapping(value="/testRpc3")
	@ResponseBody
	public List<Map<String,Object>> getTodayEmergencyVolumeTotalByMS(){
		//所有的指标id
    	List<String> indidList =  new ArrayList<>();
    	indidList.add("INDB001844");
    	//要查询的维度，就是group by中的维度
    	List<String> column=new ArrayList<>();
    	column.add("DIM0000584");
    	//column.add("DIM0000282");
    	//column.add("DIM0000003");
    	//对条件进行处理，适合mdr进行数据查询
    	Map<String, Object> args1=new HashMap<>();
    	//args1.put("STARTDATE", "2019-09-01");
    	//args1.put("ENDDATE", "2019-09-10");
    	args1.put("DIM0000001", "2019-01-01");
    	List<Map<String,Object>> list=indRpc.getIndData(indidList,column,args1,null);
    	System.out.println(list);
    	return list;
	}

	/**
	 * 首页每日-门诊收入 or 住院收入
	 *
	 * @param
	 * @param
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/getOutpatientAndHospitalizationIncome1")
	@ResponseBody
	public List<Map<String,Object>> getOutpatientAndHospitalizationIncome(){
		List<String> indIdList=new ArrayList<>();
		indIdList.add("INDC002350");
		indIdList.add("INDB002247");
		indIdList.add("INDB002248");

		Map<String,Map<String,Object>> indmap=new HashMap<>();
		Map<String,Object> innerMap1=new HashMap<>();
		innerMap1.put("indId","INDC002350");
		innerMap1.put("expression","empty");
		innerMap1.put("gatherDim","empty");
		innerMap1.put("indItemType","normal");
		indmap.put("INDC002350",innerMap1);

		Map<String,Object> innerMap2=new HashMap<>();
		innerMap2.put("indId","INDB002247");
		innerMap2.put("expression","empty");
		innerMap2.put("gatherDim","empty");
		innerMap2.put("indItemType","normal");
		indmap.put("INDB002247",innerMap2);

		Map<String,Object> innerMap3=new HashMap<>();
		innerMap3.put("indId","INDB002248");
		innerMap3.put("expression","empty");
		innerMap3.put("gatherDim","empty");
		innerMap3.put("indItemType","normal");
		indmap.put("INDB002248",innerMap3);
		
		List<String> column=new ArrayList<>();
		column.add("DIM0000114");

		List<Map<String, Object>> argList=new ArrayList<>();
		Map<String,Object> argMap=new HashMap<>();
		argMap.put("DIM0000114","45164835-9");
		argMap.put("DIM0000001","2019-01-01");
		argList.add(argMap);

		Map<String, Object> option=new HashMap<>();
		option.put("totalOption", "top");
		option.put("filterOption", null);
		option.put("line2ColOption", false);
		option.put("componetId", "v_1");
		option.put("dateType", "DIM0000003");
		option.put("searchType", null);
		
		List<Map<String,Object>> list=indRpc.queryIndDataWithMutilConditions(indIdList,indmap,column,argList,option);
		System.out.println("list====="+list);
		return list;
	}
	
	/**
	 * 门诊挂号人次his
	 *
	 * @param
	 * @param
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/testRpc4")
	@ResponseBody
	public List<Map<String,Object>> testRpc4(){
		List<String> indIdList=new ArrayList<>();
		indIdList.add("INDB002238");

		Map<String,Map<String,Object>> indmap=new HashMap<>();
		Map<String,Object> innerMap1=new HashMap<>();
		innerMap1.put("indId","INDB002238");
		innerMap1.put("indName", "门诊挂号人次his");
		innerMap1.put("indUnit", "empty");
		innerMap1.put("indPrefix", "");
		innerMap1.put("indSuffix", "");
		innerMap1.put("useSpe",true);
		innerMap1.put("indAlias", "");
		innerMap1.put("decimalLength", 0);
		innerMap1.put("expression","empty");
		innerMap1.put("gatherDim","empty");
		innerMap1.put("indItemType","normal");
		indmap.put("INDB002238",innerMap1);

		List<String> column=new ArrayList<>();
		column.add("DIM0000005");
		column.add("DIM0001651");

		List<Map<String, Object>> argList=new ArrayList<>();
		Map<String,Object> argMap=new HashMap<>();
		argMap.put("STARTDATE","2019-01-01");
		argMap.put("ENDDATE", "2019-01-02");
		argList.add(argMap);

		Map<String, Object> option=new HashMap<>();
		option.put("line2ColOption", false);
		option.put("componetId", "v_1");
		option.put("dateType", "DIM0000001");
		option.put("searchType", null);
		
		List<Map<String,Object>> list=indRpc.queryIndDataWithMutilConditions(indIdList,indmap,column,argList,option);
		System.out.println("list====="+list);
		return list;
	}

}
