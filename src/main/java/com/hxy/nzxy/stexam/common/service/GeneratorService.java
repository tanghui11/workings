/**
 * 
 */
package com.hxy.nzxy.stexam.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author 330504176@qq.com
 * @Time 2017年9月6日
 * @description
 * 
 */
@Service
public interface GeneratorService {
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);
}
