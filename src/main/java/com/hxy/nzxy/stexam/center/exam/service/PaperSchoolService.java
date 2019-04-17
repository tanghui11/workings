package com.hxy.nzxy.stexam.center.exam.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.PaperSchoolDO;

import java.util.List;
import java.util.Map;

/**
 * 阅卷点设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
public interface PaperSchoolService {
	
	PaperSchoolDO get(Long id);
	
	List<PaperSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperSchoolDO paperSchool);
	
	int update(PaperSchoolDO paperSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void set(String schoolid, List<PaperSchoolDO> conditions);

    List<PaperSchoolDO> listPlan(Map<String, Object> map);

	int countPlan(Map<String, Object> map);
}
