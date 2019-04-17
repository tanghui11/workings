package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生基本信息表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentRegionDao {

	StudentDO get(String id);
	
	List<StudentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentDO studentRegion);
	
	int update(StudentDO studentRegion);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    void updateStuStudentSpeciality(StudentSpecialityDO studentSpeciality);

    StudentSpecialityDO getByStudentId(String id);

    List<CourseDO> examTaskList(Map<String, Object> params);

    int getEnexamCount(Query query);

    void saveexam(StudentCourseDO studentCourse);

    List<SpecialityCourseDO> getids(String specialityRecordid);

    void saveBatch(List<StudentDO> userKnowledgeBaseList);
}
