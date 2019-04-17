package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 专业招生备案
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
@Mapper
public interface SchoolSpecialityRegSchoolDao {

	SchoolSpecialityRegDO get(Long id);
	
	List<SchoolSpecialityRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolSpecialityRegDO schoolSpecialityRegSchool);
	
	int update(SchoolSpecialityRegDO schoolSpecialityRegSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    SchoolSpecialityRegDO getSelect(Long schoolSpecialityRegid);

    List<SchoolSpecialityRegDO> listBk(Query query);

	int countBk(Query query);
}
