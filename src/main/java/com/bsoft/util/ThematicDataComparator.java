package com.bsoft.util;

import java.util.Comparator;
import java.util.Map;

public class ThematicDataComparator implements Comparator<Map<String,Object>> {
	//排序的key
	public String field = null;
	//排序类型 asc desc
	public String orderby = "desc"; //从大到小
	public Boolean useNumberCompare = true;
	//第一个维度是不是时间
	public Boolean fIsDate = false; 
	
	
	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		
		if(field==null)
			return 0;
		if(fIsDate){
			if("asc".equals(orderby) ){
				return ascComparefIsDate(o1,o2);
			}else if("desc".equals(orderby)){
				return descComparefIsDate(o1,o2);
			}
			return 1;
		}else{
			if("asc".equals(orderby) && useNumberCompare==true){
				return ascCompare(o1,o2);
			}else if("asc".equals(orderby) ){
				return ascCompareStr(o1,o2);
			}else if("desc".equals(orderby) && useNumberCompare==true){
				return descCompare(o1,o2);
			}else if("desc".equals(orderby)){
				return descCompareStr(o1,o2);
			}else{
				return 0;
			}
		}
	}
	
	
	private int descComparefIsDate(Map<String, Object> o1, Map<String, Object> o2) {
		if(o1.get(field)==null){
			return -1;
		}else if(o2.get(field)==null){
			return 1;
		}else{
			int dd = o1.get(field).toString().compareTo(o2.get(field).toString());
			if(dd>0){
				return -1;
			}else if(dd<0){
				return 1;
			}else{
				return 0;
			}
		}
	}


	private int ascComparefIsDate(Map<String, Object> o1, Map<String, Object> o2) {
		String[] first = field.split(",");
		String[] second = field.split(",");
		int dd =  o1.get(first[0]).toString().compareTo(o2.get(second[0]).toString());
		if(dd>0){
			return 1;
		}else if(dd<0){
			return -1;
		}else{
			double d1 = Double.parseDouble(o1.get(first[1])==null?"0":o1.get(first[1]).toString());
			double d2 = Double.parseDouble(o2.get(second[1])==null?"0":o2.get(second[1]).toString());
			if(d1>d2){
				return 1;
			}else if(d1<d2){
				return -1;
			}else{
				return 0;
			}
		}
	}


		//降序
		public int descCompareStr(Map<String, Object> o1, Map<String, Object> o2){
			if(o1.get(field)==null){
				return -1;
			}else if(o2.get(field)==null){
				return 1;
			}else{
				int dd = o1.get(field).toString().compareTo(o2.get(field).toString());
				if(dd>0){
					return -1;
				}else if(dd<0){
					return 1;
				}else{
					return 0;
				}
			}
		}
	
	//降序
	public int descCompare(Map<String, Object> o1, Map<String, Object> o2){
		if(o1.get(field)==null){
			return 1;
		}else if(o2.get(field)==null){
			return -1;
		}else{
			double d1 = Double.parseDouble(o1.get(field).toString());
			double d2 = Double.parseDouble(o2.get(field).toString());
			if(d1>d2){
				return -1;
			}else if(d1<d2){
				return 1;
			}else{
				return 0;
			}
			
//			int dd = o1.get(field).toString().compareTo(o2.get(field).toString());
//			if(dd>0){
//				return -1;
//			}else if(dd<0){
//				return 1;
//			}else{
//				return 0;
//			}
			
			
		}
	}
	
	
	//升序
		public int ascCompareStr(Map<String, Object> o1, Map<String, Object> o2){
			if(o1.get(field)==null){
				return 1;
			}else if(o2.get(field)==null){
				return -1;
			}else{
				
				int dd = o1.get(field).toString().compareTo(o2.get(field).toString());
				if(dd>0){
					return 1;
				}else if(dd<0){
					return -1;
				}else{
					return 0;
				}
				
				
			}
		}
	
	
	//升序
	public int ascCompare(Map<String, Object> o1, Map<String, Object> o2){
		if(o1.get(field)==null){
			return -1;
		}else if(o2.get(field)==null){
			return 1;
		}else{
			double d1 = Double.parseDouble(o1.get(field).toString());
			double d2 = Double.parseDouble(o2.get(field).toString());
			if(d1>d2){
				return 1;
			}else if(d1<d2){
				return -1;
			}else{
				return 0;
			}
//			
//			int dd = o1.get(field).toString().compareTo(o2.get(field).toString());
//			if(dd>0){
//				return 1;
//			}else if(dd<0){
//				return -1;
//			}else{
//				return 0;
//			}
			
			
		}
	}

}
