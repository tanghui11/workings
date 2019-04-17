package com.hxy.nzxy.stexam.common.service;

import com.hxy.nzxy.stexam.common.domain.FileDO;
import com.hxy.nzxy.stexam.common.domain.FileDO;
import com.hxy.nzxy.stexam.common.domain.FileDO;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-09-19 16:02:20
 */
public interface FileService {
	
	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDO sysFile);
	
	int update(FileDO sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
