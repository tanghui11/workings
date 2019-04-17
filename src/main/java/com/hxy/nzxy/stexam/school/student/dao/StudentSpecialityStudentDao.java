package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.domain.StudentBZCDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考生报考专业信息表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
@Mapper
public interface StudentSpecialityStudentDao {

	StudentSpecialityDO get(Long id);
	
	List<StudentSpecialityDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentSpecialityDO studentSpecialityStudent);

	int zsSave(StudentBZCDO studentSpecialityStudent);
	
	int update(StudentSpecialityDO studentSpecialityStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    StudentSpecialityDO getByStudentId(String id);

	Long getStudentSpecialityid(@Param("studentid") String studentid, @Param("specialityid") String specialityid);

	List<StudentBZCDO> listZS(List<StudentBZCDO> userKnowledgeBaseList);

	List<ScoreDO> applyExcelScore(@Param("studentid") String studentid, @Param("specialityRecordid")  String specialityRecordid);
}
