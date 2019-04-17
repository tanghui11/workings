package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 实践成绩_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface PracticeSchoolHisDao {

	PracticeSchoolHisDO get(Long id);
	
	List<PracticeSchoolHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PracticeSchoolHisDO practiceSchoolHis);
	
	int update(PracticeSchoolHisDO practiceSchoolHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
