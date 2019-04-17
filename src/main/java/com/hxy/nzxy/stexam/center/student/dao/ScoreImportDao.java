package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreImportDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 成绩导入临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface ScoreImportDao {

	ScoreImportDO get(String kemuid);
	
	List<ScoreImportDO> list(Map<String, Object> map);

	List<ScoreImportDO> listAll();

	List<ScoreImportDO> listW(List<ScoreImportDO> list);
	
	int count(Map<String, Object> map);
	
	int save(ScoreImportDO scoreImport);

	int putInFormation(ScoreImportDO scoreImportDO);
	
	int update(ScoreImportDO scoreImport);
	
	int remove(String kemuid);

	int delete();
	
	int batchRemove(String[] kemuids);

	//ScoreImportDO selectStudentid(@Param("kemuid") String kemuid,@Param("code") String code);

	int ifPutIn(@Param("code") String code,@Param("examTaskid") String examTaskid,@Param("kemuid") String kemuid);

	int setEnabled(@Param("kemuid") String kemuid, @Param("code") String code);
}