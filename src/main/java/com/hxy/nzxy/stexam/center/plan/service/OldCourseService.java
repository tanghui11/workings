package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.OldCourseDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 老课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface OldCourseService {
	
	OldCourseDO get(String id);
	
	List<OldCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OldCourseDO oldCourse);
	
	int update(OldCourseDO oldCourse);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List searchlist(Map<String,Object> params);

	String batchImport(String fileName, MultipartFile file);

    void saveBatch(List<OldCourseDO> userKnowledgeBaseList);
}
