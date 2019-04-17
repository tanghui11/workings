package com.hxy.nzxy.stexam.center.plan.dao;


import com.hxy.nzxy.stexam.domain.CourseClassifyDO;
import com.hxy.nzxy.stexam.domain.OldCourseDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 课程分类
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:51
 */
@Mapper
public interface CourseClassifyDao {


	CourseClassifyDO get(Map<String, Object> map);
	
	List<CourseClassifyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseClassifyDO courseClassify);
	
	int update(CourseClassifyDO courseClassify);
	
	int remove(Map<String, Object> map);

	int batchRemove(String[] types);

	int batchRemove(String fileName, MultipartFile file);

	List<CourseClassifyDO> listCZ(List<CourseClassifyDO> userKnowledgeBaseList);


}
