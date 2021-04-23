package com.bsoft.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeService {
	
	
	
	  List<String> inds=new ArrayList<>();
	  Map<String,Map<String,Object>> indmap=new HashMap<>();
	  List<String> column=new ArrayList<>();
	   //查询条件
      List<Map<String, Object>> argList=new ArrayList<>();
      Map<String, Object> option=new HashMap<>();
      List<Map<String,Object>> indItemsList=new ArrayList<>();
     
      Map<String,Object> indParam1=new HashMap<>();
      
      
      
      
      
     // indItemsList.add(indParam1);
      // 需要展现的列集合

   
      Map<String, Object> argMap=new HashMap<>();
  //    argMap.put("DIM0000003","2019-01");
   //   argList.add(argMap);

      //查询配置--如分页等
  //    option.put("line2ColOption",false);
   //   option.put("searchType",null);


    //List<Map<String,Object>> list1=indRpc.queryIndDataWithMutilConditions(inds,indmap,column,argList,option);

}
