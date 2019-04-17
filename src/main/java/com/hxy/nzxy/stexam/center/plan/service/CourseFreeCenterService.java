package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseFreeDO;
import com.hxy.nzxy.stexam.domain.CourseFreeCenterDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 课程免考规则
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
public interface CourseFreeCenterService {
	
	CourseFreeDO get(String id);
	
	List<CourseFreeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseFreeDO courseFreeCenter);

	int mkSave(CourseFreeCenterDO courseFreeCenter);
	
	int update(CourseFreeDO courseFreeCenter);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	String MKbatchImport(String fileName, MultipartFile file);
}
