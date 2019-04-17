package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentSpecialityService {
	
	StudentSpecialityDO get(Long id);

	StudentSpecialityDO selectStudentInfo(Long id);

	StudentSpecialityDO selectStudentSpeciality(String studentid);

	List<StudentSpecialityDO> list(Map<String, Object> map);

	List<StudentSpecialityDO> listStudent(Map<String, Object> map);

	List<StudentSpecialityDO> selectAuditStudent(Long[] ids);

	List<ScoreDO> listScore(Map<String, Object> map);
	List<ScoreDO> listScoreReplace(Map<String, Object> map);
	List<ScoreDO> applyExcelScore(String studentid,String specialityRecordid);

    int countStudent(Map<String, Object> map);

    int updateAuditStudent(Long ids, String gradAuditStatus, String gradAuditOperator);

    int updateGradute(Long ids, String gradAuditOperator);

	List<StudentSpecialityDO> listQu(Map<String, Object> map);

	List<StudentSpecialityDO> listCourseScore(Map<String, Object> map);

	List<CourseReplaceDO> listCourseReplace(String specialityRecordid);



	List<CourseAppendDO> listCourseAppend(String specialityRecordid, List<StudentSpecialityDO> studentStudentList);

	List<CourseAppendItemDO> listCourseAppendItem(List<CourseAppendDO> courseReplaceList);

	List<CourseCheckDO> listCourseCheck(String specialityRecordid, List<StudentSpecialityDO> studentStudentList);

	List<CourseCheckDO> listCourseCheckItem(Map<String, Object> map);


	List<StudentSpecialityDO> seachStuSubjectlist(Map<String, Object> map);

	List<CommonCourseReplaceDO> getCommonCourseReplace(Map<String, Object> map);

	List<CommonCourseReplaceItemDO> getItemList(List<CommonCourseReplaceDO> studentStudentList);

	int count(Map<String, Object> map);

	int countQu(Map<String, Object> map);

	int save(StudentSpecialityDO studentSpeciality);
	
	int update(StudentSpecialityDO studentSpeciality);

	int editStudentInfo(Map<String, Object> map);

	int remove(Long id);
	
	int batchRemove(Long[] ids);
	int batchUpdateAudit(Long[] ids, String auditStatus,String operator);

    int updateAudit(Map<String,Object> params);

    int updateScore(Map<String,Object> params);

    int updateScore1(Map<String,Object> params);

    int updateScoreBack(Map<String,Object> params);

    int updateScoreBack1(Map<String,Object> params);

    int editDoubleRight(String courseid,String specialityRecordid,String studentid,String type);

    String batchImport(String fileName, MultipartFile file);

    void saveBatch(List<StudentSpecialityDO> userKnowledgeBaseList);

	String batchImportKaoWu(String fileName, MultipartFile file);

	float getSourceCourse(String courseid,String specialityRecordid,String studentid);

	int selectCommonReolace(String courseid1,String courseid2);

	int selectCourseReplace(String courseid1,String courseid2,String specialityRecordid);

	int selectCourseAppend(String courseid1,String courseid2,String specialityRecordid);

	int selectCourseCheck(String courseid1,String courseid2);

	int getInfomation(String studentid,String specialityRecordid,String graduate);

	StudentSpecialityDO selectInfomation(String studentid,String specialityRecordid);

	List<StudentSpecialityDO> listStudentSpeciality(String studentid);
}
