package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.SchoolDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 助学组织
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface SchoolDao {

	SchoolDO get(Long id);
	
	List<SchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolDO school);
	
	int update(SchoolDO school);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<SchoolDO> serchSchoolAll();

    List<SchoolDO> listCZ(List<SchoolDO> userKnowledgeBaseList);
}
