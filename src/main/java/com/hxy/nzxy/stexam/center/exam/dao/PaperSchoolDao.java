package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.domain.PaperSchoolDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 阅卷点设置
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface PaperSchoolDao {

	PaperSchoolDO get(Long id);
	
	List<PaperSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaperSchoolDO paperSchool);
	
	int update(PaperSchoolDO paperSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<PaperSchoolDO> listPlan(Map<String,Object> map);

	int countPlan(Map<String,Object> map);
}
