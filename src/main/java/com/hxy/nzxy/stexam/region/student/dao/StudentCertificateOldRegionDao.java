package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 老毕业生数据
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
@Mapper
public interface StudentCertificateOldRegionDao {

	StudentCertificateOldDO get(Long id);
	
	List<StudentCertificateOldDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentCertificateOldDO studentCertificateOldRegion);
	
	int update(StudentCertificateOldDO studentCertificateOldRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
