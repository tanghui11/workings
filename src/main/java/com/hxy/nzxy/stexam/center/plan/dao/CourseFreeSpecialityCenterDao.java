package com.hxy.nzxy.stexam.center.plan.dao;

import com.hxy.nzxy.stexam.domain.CourseFreeSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程免考规则专业
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
@Mapper
public interface CourseFreeSpecialityCenterDao {

	CourseFreeSpecialityDO get(String id);
	
	List<CourseFreeSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseFreeSpecialityDO courseFreeSpecialityCenter);
	
	int update(CourseFreeSpecialityDO courseFreeSpecialityCenter);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
