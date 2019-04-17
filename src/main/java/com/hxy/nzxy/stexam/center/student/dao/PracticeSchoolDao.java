package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 实践成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface PracticeSchoolDao {

	PracticeSchoolDO get(Long id);
	
	List<PracticeSchoolDO> list(Map<String, Object> map);

//	List<PracticeSchoolDO> listT(Map<String, Object> map);

	List<PracticeSchoolDO> listL(List<PracticeSchoolDO> list);

	int count(Map<String, Object> map);

//	int countT(Map<String, Object> map);

	int save(PracticeSchoolDO practiceSchool);
	
	int update(PracticeSchoolDO practiceSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int isBK(@Param("studentid") String studentid, @Param("courseid") String courseid);

	int RK(PracticeSchoolDO practiceSchool);

	int enabled(PracticeSchoolDO practiceSchool);

	int whetherinfo(@Param("studentid") String studentid);

	int qxrk(List<PracticeSchoolDO> practiceSchool);

	int qxrkZS(List<PracticeSchoolDO> practiceSchool);

	PracticeSchoolDO cannotRK(@Param("studentid") String studentid, @Param("type") String type);

	int readyToRK(@Param("studentid") String studentid, @Param("type") String type,  @Param("grade") float grade);

	int deleteGrade(@Param("studentid") String studentid);
}
