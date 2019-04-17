package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentInDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生转入考籍
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentInRegionDao {

	StudentInDO get(Long id);
	
	List<StudentInDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentInDO studentInRegion);
	
	int update(StudentInDO studentInRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void updateMigration(StudentInDO studentInRegion);

	List<StudentInDO> migrationList(Map<String,Object> map);

	int migrationCount(Map<String,Object> map);
}
