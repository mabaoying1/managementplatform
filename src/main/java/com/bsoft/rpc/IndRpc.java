package com.bsoft.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bsoft.rpc.Rpc;
import org.springframework.stereotype.Component;

@Component
public class IndRpc extends Rpc{
	
	/*private static class LazyHolder {
	   private static final IndRpc INSTANCE = new IndRpc();    
    }    
    private IndRpc (){}    
    public static final IndRpc getInstance() {    
       return LazyHolder.INSTANCE;    
    } */
    
    /**
     * 获取指标体系
     * @return
     */
    public List<Map<String,Object>> getIndClass(){
    	Object obj = RpcInvoke("mdr.mdrRpc", "getIndClass");
		return (List<Map<String,Object>>)obj;
    }
    
    
	/**
	 * 指标中心--获取指标目录以及指标
	 * @param classid 指标分类ID
	 * @return  List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String,Object>> getMdrIndCatalog(String classid){
		Object[] params = {classid,null,null};
		Object obj = RpcInvoke("mdr.mdrRpc", "getIndTreeData", params);
		return (List<Map<String,Object>>)obj;
	}
	
	/**
	 * 指标中心--获取指标体系目录
	 * @param classid 指标分类查询所有
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getMdrIndClasses(String classid){
		Object[] params = {classid};
		Object obj = RpcInvoke("mdr.mdrRpc", "getIndClass",params);
		return (List<Map<String,Object>>)obj;
	}
	
	
	/**
	 * 指标中心--查询多指标数据
	 * @param List<String>  inds  查询指标集合
	 * @param List<String>  columns 展现维度集合
	 * @param Map<String, Object> args 查询条件
	 * @param Map<String, Object> option 分页、排序条件
	 * @return List<Map<String,Object>> 返回集合
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getIndData(List<String> inds,
			List<String> columns, Map<String, Object> args,
			Map<String, Object> option){
		if(inds==null || inds.size()==0) return null;
		Object[] params = {inds,columns,args,option};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryIndData",params);
		return (List<Map<String,Object>>)obj;
	}
	
	/**
	 * 指标中心--查询多条件多指标数据
	 * @param List<String>  inds  查询指标集合
	 * @param List<String>  columns 展现维度集合
	 * @param List<Map<String, Object>> argList 查询条件集合--多种条件
	 * [
	 * {STARTDATE:'',ENDDATE:'',DIM0000001:'C',DIM0000032:'A,B'},
	 * {STARTDATE:'',ENDDATE:'',DIM0000001:'D',DIM0000032:'A,C'},
	 * ]
	 * 
	 * @param Map<String, Object> option 分页、排序条件
	 * {
	 * 		orderOption:{
	 * 					orderField:
	 * 					orderBy:
	 * 		}
	 *  	pageOption:{
	 *  				pageSize:
	 *  				currentPage:
	 *  	}
	 *  	totalOption :
	 *  
	 * }
	 * @return List<Map<String,Object>> 返回集合
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryIndDataWithMutilConditions(List<String> inds,
			Map<String,Map<String,Object>> indmap, List<String> columns, List<Map<String, Object>> argList,
			Map<String, Object> option){
		if(inds==null || inds.size()==0) return null;
		Object[] params = {inds,indmap,columns,argList,option};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryIndDataWithMutilConditions",params);
		return (List<Map<String,Object>>)obj;
	}

	public Map<String,Object> queryData(List<Map<String,Object>> indItemsList	//指标集合
			,List<String> dimIdList	//维度集合
			,List<Map<String,Object>> dimItemsList	//时间维度
			,List<Map<String,Object>> argList	//参数值
			,Map<String, Object> option){
		if(indItemsList==null || indItemsList.size()==0) return null;
		Object[] params = {indItemsList,dimIdList,dimItemsList,argList,option};
		Object obj = RpcInvoke("hbi.thematicDataService", "queryData",params);
		return (Map<String,Object>)obj;
	}


	
	/**
	 * 指标中心--查询单指标数据
	 * @param String indid 指标id
	 * @param List<String>  columns 展现维度集合
	 * @param Map<String, Object> args 查询条件
	 * @param Map<String, Object> option 分页、排序条件
	 * @return List<Map<String,Object>> 返回集合
	 */
	public List<Map<String,Object>> getIndData(String indid,
			List<String> columns, Map<String, Object> args,
			Map<String, Object> option){
		List<String> indlist = new ArrayList<String>();
		indlist.add(indid);
		return getIndData(indlist,columns,args,option);
	}
	
	/**
	 * 指标中心--导入单指标数据
	 * @param indid 指标ID
	 * @param dataList 指标数据集合
	 */
	public void importIndData(String indid,List<Map<String,Object>> dataList){
		Object[] params = {indid,dataList};
		RpcInvoke("mdr.mdrRpc", "importIndData",params);
	}
	
	/**
	 * 根据指标id获取指标信息
	 * @param indid
	 * @return
	 */
	public Map<String,Object> getIndById(String indid){
		Object[] params = {indid};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryIndMap",params);
		return (Map<String,Object>)obj;
	}
	
	/**
	 * 判断指标表的数据量是否已经到达查询预警值
	 */
	public  Map<String,Object> isDataWarningAndNotST (List<Map<String,Object>> baseIndSet, List<String> baseDimSet){
		Object[] params = {baseIndSet,baseDimSet};
		Object obj = RpcInvoke("mdr.mdrRpc", "isDataWarningAndNotST",params);
		return (Map<String,Object>)obj;
	}
}
