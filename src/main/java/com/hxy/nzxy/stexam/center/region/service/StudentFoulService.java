package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentFoulDO;
import com.hxy.nzxy.stexam.domain.StudentWGDO;

import java.util.List;
import java.util.Map;

/**
 * 考试违规
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
public interface StudentFoulService {
	
	StudentFoulDO get(Long studentCourseid);
	
	List<StudentFoulDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentFoulDO studentFoul);
	
	int update(StudentFoulDO studentFoul);
	
	int remove(Long studentCourseid);
	
	int batchRemove(Long[] studentCourseids);

    List<StudentWGDO> listStudent(Query query);

	int countStudent(Query query);
}
