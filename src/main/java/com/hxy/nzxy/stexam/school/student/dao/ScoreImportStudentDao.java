package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreImportDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩导入临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
@Mapper
public interface ScoreImportStudentDao {

	ScoreImportDO get(String kemuid);
	
	List<ScoreImportDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreImportDO scoreImportStudent);
	
	int update(ScoreImportDO scoreImportStudent);
	
	int remove(String kemuid);
	
	int batchRemove(String[] kemuids);
}
