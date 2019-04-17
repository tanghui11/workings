package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.SubjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 科目管理
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 19:21:45
 */
@Mapper
public interface SubjectDao {

	SubjectDO get(String id);

    List<SubjectDO> list();
	
	List<SubjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SubjectDO subject);
	
	int update(SubjectDO subject);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
