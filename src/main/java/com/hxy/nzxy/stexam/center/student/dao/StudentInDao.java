package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentInDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 考生转入考籍
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentInDao {

	StudentInDO get(String id);
	
	List<StudentInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInDO studentIn);
	
	int update(StudentInDO studentIn);
	
	int remove(Long id);
	//取消调档
	int cancelStatus(Long id);

	int batchRemove(Long[] ids);


    int cancelAuditStatus(Long id);

	int auditStatus(Long id);

    List<StudentInDO> migrationList(Query query);

	int migrationCount(Query query);

    int migration(Long id);
}
