package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.domain.GroupsDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 集体设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:34
 */
public interface GroupsRegionService {
	
	GroupsDO get(Long id);
	
	List<GroupsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GroupsDO groupsRegion);
	
	int update(GroupsDO groupsRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file);
}
