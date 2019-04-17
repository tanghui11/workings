package com.hxy.nzxy.stexam.center.exam.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.TaskDO;

import java.util.List;
import java.util.Map;

/**
 * 开考课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
public interface ExamCourseResitService {
	
	ExamCourseDO get(Long id);
	
	List<ExamCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamCourseDO examCourse);
	
	int update(ExamCourseDO examCourse);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<ExamCourseDO> listProposition(Query query);

	int countProposition(Query query);

	void updateAllDefault(ExamCourseDO examCourse);

	void updateSequence(ExamCourseDO examCourse);

    void updateTask(TaskDO task);
}
