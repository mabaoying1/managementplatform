package com.bsoft.util;

import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;


public class XMLUtil {
	
	public static XMLUtil xmlUtil = null;
	
	public XMLUtil(){
		if (xmlUtil == null) {
			xmlUtil = new XMLUtil();
		}
	}
	/**
	 * 查询list
	 * @param dataCenter
	 * @return
	 */
	public static Map<String,String> getDicList(String dataCenter){
		if(dataCenter == null || ("").equals(dataCenter)){
			return null;
		}
		try {
			Document doc = DocumentHelper.parseText(dataCenter);
			Element ele = doc.getRootElement();
			Map<String, String> map = new LinkedHashMap<String, String>();
			getElementList(ele,map);
			return map;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getElementList(Element ele,Map<String, String> map){
		for(int i = 0;  i < ele.nodeCount();  i++){
			Node node = ele.node(i);
			if(node instanceof Element) {
				Element elementTemp = (Element) node;
				getElementList(elementTemp,map);
			}else{
				// 没有子元素
				String text = ele.getName();
				String key = ele.getText();
				map.put(text, key);
			}
		}
	}
	
	 @SuppressWarnings("rawtypes")
	public  static String mapToXML(Map map) {  
		 	StringBuffer  sb = new StringBuffer("");
	        Set set = map.keySet();  
	        for (Iterator it = set.iterator(); it.hasNext();) {  
	            String key = (String) it.next();  
	            Object value = map.get(key);  
	            if (null == value)  
	                value = "";  
	            if (value.getClass().getName().equals("java.util.ArrayList")) {  
	                ArrayList list = (ArrayList) map.get(key);  
	                sb.append("<" + key + ">");  
	                for (int i = 0; i < list.size(); i++) {  
	                    HashMap hm = (HashMap) list.get(i);  
	                    sb.append(  mapToXML(hm));  
	                }  
	                sb.append("</" + key + ">");  
	  
	            } else {  
	                if (value instanceof HashMap) {  
	                    sb.append("<" + key + ">");  
	                    sb.append(   mapToXML((HashMap) value));  
	                    sb.append("</" + key + ">");  
	                } else {  
	                    sb.append("<" + key + ">" + value + "</" + key + ">");  
	                }  
	  
	            }  
	  
	        }  
	        return sb.toString();
	    } 
//	public static void main(String[] args) {
//		XMLUtil.getDicList("<MEG><asd/><test1><qw>qw</qw><ww>ww</ww><as><aa>aa</aa></as></test1><USER>CS</USER><PWD>123</PWD><TYPE>01</TYPE><MODE_NAME>ehr_jbxx_jtxx</MODE_NAME></MEG>");
//	}
}
