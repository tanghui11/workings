package com.hxy.nzxy.stexam.center.sys.service;

import com.hxy.nzxy.stexam.domain.NativeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2019-03-19 11:58:48
 */
public interface NativeService {
	
	NativeDO get(String id);
	
	List<NativeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NativeDO nativeDo);
	
	int update(NativeDO nativeDo);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
