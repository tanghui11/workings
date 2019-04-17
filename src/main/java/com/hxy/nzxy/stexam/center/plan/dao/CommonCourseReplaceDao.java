package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通用课程顶替
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
@Mapper
public interface CommonCourseReplaceDao {

	CommonCourseReplaceDO get(Long id);
	
	List<CommonCourseReplaceDO> list(Map<String, Object> map);

	List<CommonCourseReplaceDO> listOther(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommonCourseReplaceDO commonCourseReplace);

	int tySave(CommonCourseReplaceDO commonCourseReplace);
	
	int update(CommonCourseReplaceDO commonCourseReplace);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String getCourseid(Long courseReplaceId);

//	List<CommonCourseReplaceDO> listTY ( List<CommonCourseReplaceDO> userKnowledgeBaseList);
}
