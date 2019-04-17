package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 开考课程
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface ExamCourseResitDao {

	ExamCourseDO get(Long id);
	
	List<ExamCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamCourseDO examCourse);
	
	int update(ExamCourseDO examCourse);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<ExamCourseDO> listByids(List<Long> ids);

    List<ExamCourseDO> listProposition(Query query);

	int countProposition(Query query);

	void updateAllDefault(ExamCourseDO examCourse);

	void updateSequence(List<ExamCourseDO> examCourse);


	List<ExamCourseDO> listSequence(ExamCourseDO examCourse);
}
