package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.StudentRegDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考生注册
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentRegDao {

	StudentRegDO get(Long id);
	
	List<StudentRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentRegDO studentReg);
	
	int update(StudentRegDO studentReg);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int updateAudit(Map<String,Object> params);

	int batchUpdateAudit(Map params);
}
