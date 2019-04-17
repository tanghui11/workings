package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 实践成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface PracticeSchoolHisRegionDao {

	PracticeSchoolHisDO get(Long id);
	
	List<PracticeSchoolHisDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PracticeSchoolHisDO practiceSchoolHisRegion);
	
	int update(PracticeSchoolHisDO practiceSchoolHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
