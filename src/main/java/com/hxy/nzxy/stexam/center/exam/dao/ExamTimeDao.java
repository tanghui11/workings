package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.domain.BookDO;
import com.hxy.nzxy.stexam.domain.ExamTimeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考试时间
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface ExamTimeDao {


	ExamTimeDO get(Long id);
	
	List<ExamTimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamTimeDO examTime);
	
	int update(ExamTimeDO examTime);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<ExamTimeDO> seachExamTimeAllList(Map<String,Object> params);

    void saveBatch(List<ExamTimeDO> userKnowledgeBaseList);

	List<ExamTimeDO> listCZ(List<ExamTimeDO> userKnowledgeBaseList);
}
