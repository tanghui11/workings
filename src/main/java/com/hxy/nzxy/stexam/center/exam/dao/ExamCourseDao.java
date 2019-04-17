package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.ListPlaceDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 开考课程
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface ExamCourseDao {

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

	void updateAudit(ExamCourseDO examCourse);

	void updateSequence(List<ExamCourseDO> examCourse);


	List<ExamCourseDO> listSequence(ExamCourseDO examCourse);

	List<ListPlaceDO> listPlace(String courseid);

	Integer addPlaceCourse(@Param("courseid") Long courseid, @Param("id") Long id, @Param("operator") String operator);

	int ifPlaceCourse(@Param("courseid") Long courseid, @Param("id") Long id, @Param("operator") String operator);

	Integer deletePlaceCourse(@Param("courseid") Long courseid, @Param("id") Long id, @Param("operator") String operator);

	List<ExamCourseDO> listPl(String courseid);

	ExamCourseDO listPP(Long specialityRecordid);


	void saveBatch(List<ExamCourseDO> userKnowledgeBaseList);

	List<ExamCourseDO> listCZ(List<ExamCourseDO> userKnowledgeBaseList);
}
