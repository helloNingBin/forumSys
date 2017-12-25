package com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtils
{
  public static String setErrorMsg(String msg)
  {
    JSONObject json = new JSONObject();
    json.put("result", Boolean.valueOf(false));
    json.put("msg", msg);
    return json.toString();
  }

  public static String success()
  {
    JSONObject json = new JSONObject();
    json.put("result", Boolean.valueOf(true));
    return json.toString();
  }

  public static String success(String data)
  {
    JSONObject json = new JSONObject();
    json.put("result", Boolean.valueOf(true));
    json.put("data", data);
    return json.toString();
  }

  public static String success(List<?> list)
  {
    JSONObject json = new JSONObject();
    json.put("result", Boolean.valueOf(true));
    json.put("msg", list);
    return json.toString();
  }

  public static Map<String, Object> parseJSON2Map(String jsonStr)
  {
    Map map = new HashMap();
    if (StringUtil.isNull(jsonStr)) {
      return null;
    }
    JSONObject jsonObject = JSONObject.fromObject(jsonStr);
    for (Iterator localIterator = jsonObject.keySet().iterator(); localIterator.hasNext(); ) { Object keyObj = localIterator.next();
      String key = (String)keyObj;
      Object value = jsonObject.get(key);
      if ((value instanceof JSONArray)) {
        List list = null;
        JSONArray josnArray = JSONArray.fromObject(value);
        if (josnArray != null) {
          int size = josnArray.size();
          if (size > 0) {
            list = new ArrayList();
          }
          for (int i = 0; i < size; i++) {
            String strValue = josnArray.getString(i);
            list.add(parseJSON2Map(strValue));
          }
        }
        map.put(key, list);
      } else if ((value instanceof JSONObject)) {
        JSONObject jsonObjectValue = JSONObject.fromObject(value);
        map.put(key, parseJSON2Map(jsonObjectValue.toString()));
      } else {
        map.put(key, value);
      }
    }
    return map;
  }
}