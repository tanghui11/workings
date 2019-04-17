package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.domain.ExamItemDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考试项目
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface ExamItemDao {

	ExamItemDO get(Long id);
	
	List<ExamItemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamItemDO examItem);
	
	int update(ExamItemDO examItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
