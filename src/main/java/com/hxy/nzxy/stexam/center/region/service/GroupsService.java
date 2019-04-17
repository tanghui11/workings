package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.domain.GroupsDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 集体设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:18
 */
public interface GroupsService {
	
	GroupsDO get(Long id);
	
	List<GroupsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GroupsDO groups);
	
	int update(GroupsDO groups);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    //批量导入
    String batchImport(String fileName, MultipartFile file);

	void saveBatch(List<GroupsDO> userKnowledgeBaseList);
}
