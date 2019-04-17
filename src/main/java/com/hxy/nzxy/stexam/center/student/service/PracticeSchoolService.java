package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 实践成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface PracticeSchoolService {
	
	PracticeSchoolDO get(Long id);
	
	List<PracticeSchoolDO> list(Map<String, Object> map);

//	List<PracticeSchoolDO> listT(Map<String, Object> map);

	int count(Map<String, Object> map);
//	int countT(Map<String, Object> map);

	int whetherinfo(String studentid);

	int save(PracticeSchoolDO practiceSchool);

	int enabled(PracticeSchoolDO practiceSchool);

	int RK(PracticeSchoolDO practiceSchool);

	int update(PracticeSchoolDO practiceSchool);

	int qxrk(List<PracticeSchoolDO> practiceSchool);

	int qxrkZS(List<PracticeSchoolDO> practiceSchool);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file);

	int isBK(String studentid, String courseid);

	PracticeSchoolDO cannotRK(String studentid, String type);

	int readyToRK(String studentid, String type, float grade);

	int deleteGrade(String studentid);
}
