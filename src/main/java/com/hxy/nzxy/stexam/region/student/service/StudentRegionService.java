package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentRegionService {
	
	StudentDO get(String id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO studentRegion, StudentSpecialityDO studentSpeciality);
	
	int update(StudentDO studentRegion, StudentSpecialityDO studentSpeciality);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    StudentSpecialityDO getByStudentId(String id);

    List<CourseDO> examTaskList(Map<String, Object> params);

    int getEnexamCount(Query query);

    int saveexam(StudentCourseDO studentCourse);

	List<SpecialityCourseDO> getids(String specialityRecordid);
	TaskDO getLastTask(Map<String,Object> params);


}
