package com.hxy.nzxy.stexam.common.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {
	/**
	 * Bean对象转JSON
	 * 
	 * @param object
	 * @param dataFormatString
	 * @return
	 */
	public static String beanToJson(Object object, String dataFormatString) {
		if (object != null) {
			if (StringUtils.isEmpty(dataFormatString)) {
				return JSONObject.toJSONString(object);
			}
			return JSON.toJSONStringWithDateFormat(object, dataFormatString);
		} else {
			return null;
		}
	}

	/**
	 * Bean对象转JSON
	 * 
	 * @param object
	 * @return
	 */
	public static String beanToJson(Object object) {
		if (object != null) {
			return JSON.toJSONString(object);
		} else {
			return null;
		}
	}

	/**
	 * String转JSON字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String stringToJsonByFastjson(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>(16);
		map.put(key, value);
		return beanToJson(map, null);
	}

	/**
	 * 将json字符串转换成对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object jsonToBean(String json, Object clazz) {
		if (StringUtils.isEmpty(json) || clazz == null) {
			return null;
		}
		return JSON.parseObject(json, clazz.getClass());
	}
	/**
	 * 将json字符串转换成List
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> readListValue(String json, Class<T> tClazz){
		return JSON.parseArray(json, tClazz);
	}

	/**
	 * json字符串转map
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, Map.class);
	}

	public static <T> List<T> jsonToList(String json, Class<T> clz){
		return JSON.parseArray(json,clz);
	}



	/**
	 * Bean对象转JSON
	 *
	 * @param object
	 * @return
	 */
	public static Object stringToJson(String string) {
		if (string != null) {
			return JSON.parse(string);
		} else {
			return null;
		}
	}
}
