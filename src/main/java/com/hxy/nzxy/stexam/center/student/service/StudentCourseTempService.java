package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 考生报考临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentCourseTempService {
	
	StudentCourseTempDO get(Long id);
	
	List<StudentCourseTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseTempDO studentCourseTemp);
	
	int update(StudentCourseTempDO studentCourseTemp);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String batchImport(String fileName, MultipartFile file) throws IOException;

}

