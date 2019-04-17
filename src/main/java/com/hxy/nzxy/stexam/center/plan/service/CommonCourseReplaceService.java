package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 通用课程顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CommonCourseReplaceService {
	
	CommonCourseReplaceDO get(Long id);
	
	List<CommonCourseReplaceDO> list(Map<String, Object> map);

	List<CommonCourseReplaceDO> listOther(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommonCourseReplaceDO commonCourseReplace);
	
	int update(CommonCourseReplaceDO commonCourseReplace);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String TYbatchImport(String fileName, MultipartFile file);

	String getFatherCourse(String courseReplaceId);
}
