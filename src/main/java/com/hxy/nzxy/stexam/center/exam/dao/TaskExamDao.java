package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 考试任务
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 16:59:07
 */
@Mapper
public interface TaskExamDao {

	TaskDO get(Long id);
	
	List<TaskDO> list(List<TaskDO> map);

	int count(Map<String, Object> map);
	
	int save(TaskDO task);
	
	int update(TaskDO task);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<TaskDO> serchTaskAll(Map<String, Object> map);

	int updateStatus(TaskDO task);

	TaskDO getLastTask(Map<String,Object> params);

	List<TaskDO> taskList(Map<String,Object> map);

    List<TaskDO> list(Map<String,Object> map);

    TaskDO saveBatch(List<TaskDO> userKnowledgeBaseList);

	List<TaskDO> seachBkTimeSetlist(Map<String,Object> map);

    int bkcount(Long[] id);
}
