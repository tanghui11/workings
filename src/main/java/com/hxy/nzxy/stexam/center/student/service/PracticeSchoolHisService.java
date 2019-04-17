package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;

import java.util.List;
import java.util.Map;

/**
 * 实践成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface PracticeSchoolHisService {
	
	PracticeSchoolHisDO get(Long id);
	
	List<PracticeSchoolHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PracticeSchoolHisDO practiceSchoolHis);
	
	int update(PracticeSchoolHisDO practiceSchoolHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
