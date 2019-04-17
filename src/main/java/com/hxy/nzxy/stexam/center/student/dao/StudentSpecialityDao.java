package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.ParameterScriptAssert;

/**
 * 表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentSpecialityDao {

	StudentSpecialityDO get(Long id);

	StudentSpecialityDO selectStudentInfo(Long id);

	StudentSpecialityDO selectStudentSpeciality(String studentid);
	
	List<StudentSpecialityDO> list(Map<String, Object> map);

	List<StudentSpecialityDO> listStudent(Map<String, Object> map);

	List<StudentSpecialityDO> selectAuditStudent(Long[] ids);

	List<StudentSpecialityDO> listQu(Map<String, Object> map);

	List<StudentSpecialityDO> listCourseScore(Map<String, Object> map);

	List<ScoreDO> listScore(Map<String, Object> map);
	List<ScoreDO> listScoreReplace(Map<String, Object> map);
	List<ScoreDO> applyExcelScore(@Param("studentid") String studentid,@Param("specialityRecordid") String specialityRecordid);

	int count(Map<String, Object> map);

	int countStudent(Map<String, Object> map);

	int countQu(Map<String, Object> map);

	int updateAuditStudent(@Param("ids") Long ids,@Param("gradAuditStatus") String gradAuditStatus,@Param("gradAuditOperator") String gradAuditOperator);

	int updateGradute(@Param("ids") Long ids,@Param("gradAuditOperator") String gradAuditOperator);

	int save(StudentSpecialityDO studentSpeciality);
	
	int update(StudentSpecialityDO studentSpeciality);
	
	int remove(Long id);

	int editStudentInfo(Map<String, Object> map);
	
	int batchRemove(Long[] ids);

    List<StudentSpecialityDO> seachStuSubjectlist(Map<String,Object> map);

	int batchUpdateAudit(Map map);

    int updateAudit(Map<String,Object> params);
    int updateScore(Map<String,Object> params);
    int updateScore1(Map<String,Object> params);
    int updateScoreBack(Map<String,Object> params);
    int updateScoreBack1(Map<String,Object> params);

    List<StudentSpecialityDO> listCZ(List<StudentSpecialityDO> userKnowledgeBaseList);

	void saveBatch(List<StudentSpecialityDO> userKnowledgeBaseList);

	List<CommonCourseReplaceDO> getCommonCourseReplace(Map<String,Object> params);

	List<CommonCourseReplaceItemDO> getItemList(List<CommonCourseReplaceDO> studentStudentList);

	int editDoubleRight(@Param("courseid") String courseid, @Param("specialityRecordid") String specialityRecordid,@Param("studentid") String studentid, @Param("type") String type);

    float getSourceCourse(@Param("courseid") String courseid,@Param("specialityRecordid") String specialityRecordid,@Param("studentid") String studentid);

	List<CourseReplaceDO> listCourseReplace(@Param("specialityRecordid")String specialityRecordid);

	List<CourseAppendDO> listCourseAppend(@Param("specialityRecordid") String specialityRecordid, @Param("list") List<StudentSpecialityDO> studentStudentList);

	List<CourseAppendItemDO> listCourseAppendItem(List<CourseAppendDO> courseReplaceList);

	List<CourseCheckDO> listCourseCheck(@Param("specialityRecordid") String specialityRecordid, @Param("list") List<StudentSpecialityDO> studentStudentList);

	List<CourseCheckDO> listCourseCheckItem(Map<String,Object> params);

	int selectCommonReolace(@Param("courseid1") String courseid1,@Param("courseid2")String courseid2);

	int selectCourseReplace(@Param("courseid1") String courseid1,@Param("courseid2")String courseid2,@Param("specialityRecordid") String specialityRecordid);

	int selectCourseAppend(@Param("courseid1") String courseid1,@Param("courseid2")String courseid2,@Param("specialityRecordid") String specialityRecordid);

	int selectCourseCheck(@Param("courseid1") String courseid1,@Param("courseid2")String courseid2);

	int getInfomation(@Param("studentid") String studentid,@Param("specialityRecordid") String specialityRecordid,@Param("graduate") String graduate);
	StudentSpecialityDO selectInfomation(@Param("studentid") String studentid,@Param("specialityRecordid") String specialityRecordid);
	List<StudentSpecialityDO> listStudentSpeciality(@Param("studentid") String studentid);

    List<StudentSpecialityDO> listSpeciality(Map map);
}
