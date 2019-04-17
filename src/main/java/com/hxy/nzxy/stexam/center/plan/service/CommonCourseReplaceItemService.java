package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemNewDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 通用顶替课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CommonCourseReplaceItemService {
	
	CommonCourseReplaceItemDO get(Long id);
	
	List<CommonCourseReplaceItemDO> list(Map<String, Object> map);

	List<CommonCourseReplaceItemNewDO> itemList(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommonCourseReplaceItemDO commonCourseReplaceItem);
	
	int update(CommonCourseReplaceItemDO commonCourseReplaceItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String TYIbatchImport(Long fatherCourseId, String fileName, MultipartFile file);
}
