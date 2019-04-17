package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentStudentService {
	
	StudentDO get(String id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO studentStudent,StudentSpecialityDO studentSpeciality);
	
	int update(StudentDO studentStudent,StudentSpecialityDO studentSpeciality);
	int updateNMD(StudentDO studentStudent);

	int remove(String id);
	
	int batchRemove(String[] ids);

    String batchImport(String fileName, MultipartFile file,String teachid,String collegeid);

    void saveBatch(List<StudentDO> userKnowledgeBaseList);
}
