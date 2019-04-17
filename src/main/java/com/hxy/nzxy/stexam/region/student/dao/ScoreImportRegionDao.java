package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreImportDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩导入临时表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreImportRegionDao {

	ScoreImportDO get(String kemuid);
	
	List<ScoreImportDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreImportDO scoreImportRegion);
	
	int update(ScoreImportDO scoreImportRegion);
	
	int remove(String kemuid);
	
	int batchRemove(String[] kemuids);
}
