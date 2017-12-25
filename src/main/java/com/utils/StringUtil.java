package com.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

public class StringUtil{
  private static final Logger LOG = Logger.getLogger(StringUtil.class);
  
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
			  sb.append(entry.getKey() + ":" + entry.getValue().toString());
		  }else{
			  sb.append(";" + entry.getKey() + ":" + entry.getValue());
		  }
	  }
	  sb.append("]");
	  return sb.toString();
  }

  public static String reEncodingAndDecoding(String string, String encoding, String decoding)
  {
    try
    {
      return new String(string.getBytes(encoding), decoding);
    } catch (Exception e) {
      LOG.error("", e);
    }
    return string;
  }

  public static String getStrByList(List<String> strs)
  {
    StringBuffer sb = new StringBuffer("");
    if (strs != null) {
      int i = 0; for (int len = strs.size(); i < len; i++) {
        String s = (String)strs.get(i);
        if (StringUtils.isNotBlank(s)) {
          if (i >= 1)
            sb.append(",'").append(s).append("'");
          else {
            sb.append("'").append(s).append("'");
          }
        }
      }
    }
    return sb.toString();
  }

  public static String getNewStrByList2(List<Long> strs)
  {
    StringBuffer sb = new StringBuffer("");
    if (strs != null) {
      int i = 0; for (int len = strs.size(); i < len; i++) {
        String l = ((Long)strs.get(i)).toString();
        if (StringUtils.isNotBlank(l)) {
          if (i >= 1)
            sb.append(",").append(l);
          else {
            sb.append(l);
          }
        }
      }
    }
    return sb.toString();
  }

  public static String subStringByDo(String string)
  {
    if ((string == null) || (string.length() == 0))
      return "";
    try
    {
      int doIndex = string.indexOf(".do");
      int spritLastIndex = string.lastIndexOf("/") + 1;
      return string.substring(spritLastIndex, doIndex);
    } catch (StringIndexOutOfBoundsException e) {
      LOG.error("string : " + string + ", 切割角标异常");
    } catch (Exception e) {
      LOG.error("string : " + string + ", 切割出现异常");
    }
    return "";
  }

  public static boolean isNull(String str)
  {
    if (str == null)
      return true;
    if (str.trim().length() == 0)
      return true;
    return false;
  }

  public static String getCode()
    throws Exception
  {
    return String.valueOf(getIntRandomNum(100000, 999999));
  }

  public static int getIntRandomNum(int min, int max)
    throws Exception
  {
    double c = Math.random() * (max - min) + min;
    return (int)c;
  }

  public static String replaceName(String name)
  {
    StringBuilder sb = new StringBuilder();
    if (!name.contains("·")) {
      if ((name.length() >= 3) && (name.length() <= 4)) {
        name = "**" + name.substring(name.length() - 2, name.length());
      }
      else if (name.length() == 5) {
        name = "**" + name.substring(name.length() - 3, name.length());
      }
      else if (name.length() == 2)
        name = "**" + name.substring(name.length() - 1);
    }
    else {
      name = "**" + name.substring(name.lastIndexOf("·") + 1, name.length());
    }
    return name;
  }

  public static InputStream uploadHeadImg(String file)
    throws IOException
  {
    String newFile = null;
    if ((file != null) && (!"".equals(file))) {
      String[] split = file.split("base64,");
      if (split.length > 0) {
        newFile = split[1];
      }
    }
    if (newFile != null)
    {
      BASE64Decoder decoder = new BASE64Decoder();

      byte[] b = decoder.decodeBuffer(newFile);
      for (int i = 0; i < b.length; i++) {
        if (b[i] < 0)
        {
          int tmp65_63 = i;
          byte[] tmp65_62 = b; tmp65_62[tmp65_63] = ((byte)(tmp65_62[tmp65_63] + 256));
        }
      }
      ByteArrayInputStream input = new ByteArrayInputStream(b);
      return input;
    }
    return null;
  }
}