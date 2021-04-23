package com.bsoft.rpc;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DimRpc extends Rpc{
	
	/*private static class LazyHolder {
	   private static final DimRpc INSTANCE = new DimRpc();    
    }    
    private DimRpc (){}    
    public static final DimRpc getInstance() {    
       return LazyHolder.INSTANCE;    
    }   */
	
	/**
	 * 指标中心--同步维度数据
	 */
    public void synMdrDimData(String platformDicId,List<Map<String,Object>> dicDataList,String type){
		Object[] params = {platformDicId,dicDataList,type};
		RpcInvoke("mdr.mdrRpc", "synMdrDimData",params);
	}
	
	public void synMdrDimData(String platformDicId,Map<String,Object> dicData){
		
	}
	
	/**
	 * 指标中心--获取指标公共维度
	 * @param indArr List<String> 指标集合
	 * @return  List<Map<String, Object>> 维度集合
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommonDimData(List<String> indArr){
		Object[] params = {indArr};
		Object obj = RpcInvoke("mdr.mdrRpc", "getCommonDimData",params);
		return (List<Map<String, Object>>)obj;
	}
	
	/**
	 * 指标中心--获取维度列表
	 * @return List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getMdrDimCatalog(){
		Object obj = RpcInvoke("mdr.mdrRpc", "queryDimTree");
		return (List<Map<String,Object>>)obj;
	}
	
	/**
	 * 指标中心 --获取维度数据
	 * @param dimid 维度ID
	 * @return List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getMdrDimData(String dimid){
		Object[] params = {dimid,false};
		Map<String,Object> map = (Map<String,Object>)RpcInvoke("mdr.mdrRpc", "queryDimData",params);
		List<Map<String,Object>> datalist = (List<Map<String,Object>>)map.get("dataList");
		return datalist;
	}
	
	/**
	 * 指标中心--导入单维度数据
	 * @param dimid 维度ID
	 * @param dataList 维度数据
	 */
	public void importDimData(String dimid,List<Map<String,Object>> dataList){
		Object[] params = {dimid,dataList};
		RpcInvoke("mdr.mdrRpc", "importDimData",params);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectIndReDirectDateDim(String indid){
		Object[] params = {indid};
		Object obj = RpcInvoke("mdr.mdrRpc", "selectIndReDirectDateDim",params);
		return (Map<String,Object>)obj;
	}
	
	/**
	 * 获取某维度值对应的子维度数据
	 * @param dimId 维度id
	 * @param dimvalue  维度数据
	 * @return 维度数据集合
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryRelationDimDataMap(String dimId,Object dimvalue){
		Object[] params = {dimId,dimvalue};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryRelationDimDataMap",params);
		return (Map<String,Object>)obj;
	}
	
	/**
	 * 查询维度所有层次(会有重复节点，如通过日查询，年节点重复)
	 * @param dimId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryDimLevelMaps(String dimId){
		Object[] params = {dimId};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryDimLevelMaps",params);
		return (List<Map<String,Object>>)obj;
	}

	/**
	 * 查询维度所有层次（去除重复节点）
	 * @param dimId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryDistinctDimLevelMaps(String dimId){
		Object[] params = {dimId};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryDistinctDimLevelMaps",params);
		return (List<Map<String,Object>>)obj;
	}
	
	/**
	 * 查询维度基本信息
	 * @param dimId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getDimById(String dimId){
		Object[] params = {dimId};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryDimMap",params);
		return (Map<String,Object>)obj;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryDimDataBySQL(String sql){
		Object[] params = {sql};
		Object obj = RpcInvoke("mdr.dimDataService", "queryDimDataBySQL",params);
		return (List<Map<String,Object>>)obj;
	}
	/**
	 * 根据dimid查询层级关系
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Map<String,Object>> queryRelationDimDataMapBydimId(String dimId){
		Object[] params = {dimId};
		Object obj = RpcInvoke("mdr.mdrRpc", "queryRelationDimDataMapBydimId",params);
		return (Map<String,Map<String,Object>>)obj;
	}
}
