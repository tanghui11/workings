package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 毕业证书管理
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
@Mapper
public interface StudentCertificateDao {

	StudentCertificateDO get(String id);
	
	List<StudentCertificateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCertificateDO studentCertificate);
	
	int update(StudentCertificateDO studentCertificate);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<StudentCertificateDO> listDiploma(Map<String, Object> map);

	int countDiploma(Query query);

    String isCode(Long id);

    List<StudentCertificateDO> initCode();
}
