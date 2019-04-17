package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseClassifyDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 课程分类
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:51
 */
public interface CourseClassifyService {
	
	CourseClassifyDO get(String type, String courseid);
	
	List<CourseClassifyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseClassifyDO courseClassify);
	
	int update(CourseClassifyDO courseClassify);
	
	int remove(String type, String courseid);
	
	int batchRemove(String[] types);

	String batchImport(String fileName, MultipartFile file);


}
