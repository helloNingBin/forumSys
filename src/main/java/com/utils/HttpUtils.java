package com.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author bin
 *2017.12.1
 *封装了http的工具
 */
public class HttpUtils {
	  
	  /**
	   * 把一个Map转化为字符串
	   * 格式：[k1:v1;k2:v2....]
	 * @param map  Map需指定类型，String,Object
	 * @return
	 */
	public static String mapToString(Map<String,?> map){
		  if(map == null){
			  return "";
		  }
		  StringBuilder sb = new StringBuilder("[");
		  for(Entry<String,?> entry : map.entrySet()){
			  if(sb.length() == 1){
				  //request.getParameterMap()中的value是一个String数组
				  sb.append(entry.getKey() + ":" + Arrays.toString((String[]) entry.getValue()));
			  }else{
				  sb.append(";" + entry.getKey() + ":" + Arrays.toString((String[]) entry.getValue()));
			  }
		  }
		  sb.append("]");
		  return sb.toString();
	  }

}
