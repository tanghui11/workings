package com.hxy.nzxy.stexam.region.exam.dao;

import com.hxy.nzxy.stexam.domain.PaperSchoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 阅卷点设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
@Mapper
public interface PaperSchoolRegionDao {

	PaperSchoolDO get(Long id);
	
	List<PaperSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperSchoolDO paperSchoolRegion);
	
	int update(PaperSchoolDO paperSchoolRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
